package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.database.*;
import com.ifelseelif.touristapp.models.dto.*;
import com.ifelseelif.touristapp.models.domain.*;
import com.ifelseelif.touristapp.service.interfaces.IModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeratorService implements IModeratorService {

    private final ReportRepository reportRepository;
    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;
    private final StoryRepository storyRepository;
    private final MaterialRepository materialRepository;
    private final MaterialRequestRepository materialRequestRepository;
    private final MaterialService materialService;
    private final AnswerMessageService answerMessageService;

    @Autowired
    public ModeratorService(ReportRepository reportRepository, CountryRepository countryRepository,
                            HotelRepository hotelRepository, StoryRepository storyRepository, MaterialRepository materialRepository,
                            MaterialRequestRepository materialRequestRepository, MaterialService materialService, AnswerMessageService answerMessageService) {
        this.reportRepository = reportRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.storyRepository = storyRepository;
        this.materialRepository = materialRepository;
        this.materialRequestRepository = materialRequestRepository;
        this.materialService = materialService;
        this.answerMessageService = answerMessageService;
    }

    @Override
    public List<Report> getAllReports() {
        return (List<Report>) reportRepository.findAll();
    }

    @Override
    public Report getReport(long id) {
        return reportRepository.findReportById(id);
    }

    @Override
    public void addCountry(String name) {
        Country country = new Country();
        country.setName(name);
        this.countryRepository.save(country);
    }

    @Override
    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setCountry(new Country(hotelDto.getCountryId()));
        hotel.setRating(hotelDto.getRating());

        hotelRepository.save(hotel);
    }

    @Override
    public List<StoryResponse> getUnverifiedStories() {
        List<Story> dbStories = storyRepository.findAllUnverifiedStories();
        List<StoryResponse> unverifiedStories = new ArrayList<>();

        for (Story story : dbStories) {
            unverifiedStories.add(StoryResponse.builder()
                    .id(story.getId())
                    .header(story.getHeader())
                    .travelDate(story.getTravelDate())
                    .briefInformation(story.getBriefInformation())
                    .mainText(story.getMainText())
                    .countries(story.getCountry().stream().map(Country::getName).collect(Collectors.toSet()))
            .build());
        }

        return unverifiedStories;
    }

    @Override
    public void setVerifiedStory(long id) {
        Optional<Story> dbStory = storyRepository.findById(id);

        if (dbStory.isPresent()) {

            Optional<Material> dbMaterial = Optional.ofNullable(materialRepository.findMaterialByStory(dbStory.get()));

            if ((dbMaterial.isPresent()) && (!dbMaterial.get().getStatus().equals(Status.PUBLISHED))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Story isn't published");
            }

            if (dbStory.get().isVerified()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Story already been verified");
            }


        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found");
        }

        storyRepository.setVerifiedStory(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishMaterial(long id) {
        Optional<MaterialRequest> dbMaterialRequest = materialRequestRepository.findById(id);
        Optional<Material> dbMaterial = dbMaterialRequest.map(MaterialRequest::getMaterial);

        if (dbMaterial.isPresent()) {
            if (dbMaterial.get().getTypeMaterial().equals(TypeMaterial.STORY)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect type of material");
            }

            if (!dbMaterial.get().getStatus().equals(Status.APPROVING)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            Optional<MaterialRequest> materialRequest = materialRequestRepository.findById(id);

            if (materialRequest.isPresent()) {
                answerMessageService.sendAnswerMessage(new ModeratorAnswer(materialRequest.get(), "", RequestStatus.APPROVED));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material request not found");
            }

            materialRepository.changeStatus(materialRequest.get().getMaterial().getId(), Status.PUBLISHED);
            materialRequestRepository.changeRequestStatus(id, RequestStatus.APPROVED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material not found");
        }

    }

    @Override
    public void rejectMaterial(ModeratorRejectRequest moderatorRejectRequest) {
        Optional<MaterialRequest> dbMaterialRequest = materialRequestRepository.findById(moderatorRejectRequest.getId());
        Optional<Material> dbMaterial = dbMaterialRequest.map(MaterialRequest::getMaterial);

        if (dbMaterial.isPresent()) {
            if (dbMaterial.get().getTypeMaterial().equals(TypeMaterial.STORY)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect type of material");
            }

            if (!dbMaterial.get().getStatus().equals(Status.APPROVING)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            if (!dbMaterialRequest.get().getRequestStatus().equals(RequestStatus.UNCHECKED)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material request");
            }

            Optional<MaterialRequest> materialRequest = materialRequestRepository.findById(moderatorRejectRequest.getId());

            if (materialRequest.isPresent()) {
                answerMessageService.sendAnswerMessage(
                        new ModeratorAnswer(materialRequest.get(), moderatorRejectRequest.getReason(), RequestStatus.REJECTED));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material request not found");
            }

            materialRequestRepository.changeRequestStatus(moderatorRejectRequest.getId(), RequestStatus.REJECTED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material not found");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeReport(ReviewedReport reviewedReport) {
        Optional<Report> dbReport = reportRepository.findById(reviewedReport.getReportId());

        if (dbReport.isPresent()) {

            reportRepository.deleteById(reviewedReport.getReportId());

            if (!reviewedReport.isMaterialGood()) {
                materialService.deleteMaterial(dbReport.get().getMaterial().getId());
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
    }

    @Override
    public void selectBestMaterial(long idOfMaterial) {
        Material material = materialRepository.findById(idOfMaterial).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Material with id=" + idOfMaterial + " not found")
        ));
        material.setBest(true);

        materialRepository.save(material);
    }

    @Override
    public List<MaterialRequest> getAllMaterialRequests() {
        return materialRequestRepository.findAllRequests(RequestStatus.UNCHECKED);
    }
}
