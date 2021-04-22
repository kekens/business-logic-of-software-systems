package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.*;
import com.ifelseelif.blsslab1.models.dto.*;
import com.ifelseelif.blsslab1.models.domain.*;
import com.ifelseelif.blsslab1.service.interfaces.IModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    @Autowired
    public ModeratorService(ReportRepository reportRepository, CountryRepository countryRepository,
                            HotelRepository hotelRepository, StoryRepository storyRepository, MaterialRepository materialRepository, MaterialRequestRepository materialRequestRepository, MaterialService materialService) {
        this.reportRepository = reportRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.storyRepository = storyRepository;
        this.materialRepository = materialRepository;
        this.materialRequestRepository = materialRequestRepository;
        this.materialService = materialService;
    }

    @Override
    public List<DbReport> getAllReports() {
        return (List<DbReport>) reportRepository.findAll();
    }

    @Override
    public DbReport getReport(long id) {
        return reportRepository.findDbReportById(id);
    }

    @Override
    public void addCountry(String name) {
        DbCountry dbCountry = new DbCountry();
        dbCountry.setName(name);
        this.countryRepository.save(dbCountry);
    }

    @Override
    public void addHotel(Hotel hotel) {
        DbHotel dbHotel = new DbHotel();
        dbHotel.setName(hotel.getName());
        dbHotel.setCountry(new DbCountry(hotel.getCountryId()));
        dbHotel.setRating(hotel.getRating());

        hotelRepository.save(dbHotel);
    }

    @Override
    public List<StoryResponse> getUnverifiedStories() {
        List<DbStory> dbStories = storyRepository.findAllUnverifiedStories();
        List<StoryResponse> unverifiedStories = new ArrayList<>();

        for (DbStory dbStory: dbStories) {
            unverifiedStories.add(StoryResponse.builder()
                    .id(dbStory.getId())
                    .header(dbStory.getHeader())
                    .travelDate(dbStory.getTravelDate())
                    .briefInformation(dbStory.getBriefInformation())
                    .mainText(dbStory.getMainText())
                    .countries(dbStory.getCountry().stream().map(DbCountry::getName).collect(Collectors.toSet()))
            .build());
        }

        return unverifiedStories;
    }

    @Override
    public void setVerifiedStory(long id) {
        Optional<DbStory> dbStory = storyRepository.findById(id);

        if (dbStory.isPresent()) {

            Optional<DbMaterial> dbMaterial = Optional.ofNullable(materialRepository.findDbMaterialByStory(dbStory.get()));

            if (dbMaterial.isPresent()) {
                if (!dbMaterial.get().getStatus().equals(Status.Published)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Story isn't published");
                }
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
    public void publishMaterial(long id) {
        Optional<DbMaterialRequest> dbMaterialRequest = materialRequestRepository.findById(id);
        Optional<DbMaterial> dbMaterial = dbMaterialRequest.map(DbMaterialRequest::getDbMaterial);

        if (dbMaterial.isPresent()) {
            if (dbMaterial.get().getTypeMaterial().equals(TypeMaterial.Story)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect type of material");
            }

            if (!dbMaterial.get().getStatus().equals(Status.Approving)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            materialRepository.changeStatus(dbMaterial.get().getId(), Status.Published);
            materialRequestRepository.changeRequestStatus(id, RequestStatus.Approved);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material not found");
        }

    }

    @Override
    public void rejectMaterial(long id) {
        Optional<DbMaterialRequest> dbMaterialRequest = materialRequestRepository.findById(id);
        Optional<DbMaterial> dbMaterial = dbMaterialRequest.map(DbMaterialRequest::getDbMaterial);

        if (dbMaterial.isPresent()) {
            if (dbMaterial.get().getTypeMaterial().equals(TypeMaterial.Story)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect type of material");
            }

            if (!dbMaterial.get().getStatus().equals(Status.Approving)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            if (!dbMaterialRequest.get().getRequestStatus().equals(RequestStatus.Unchecked)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material request");
            }
            materialRequestRepository.changeRequestStatus(id, RequestStatus.Rejected);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material not found");
        }

    }

    @Override
    public void closeReport(ReviewedReport reviewedReport) {
        Optional<DbReport> dbReport = reportRepository.findById(reviewedReport.getReportId());

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
        DbMaterial dbMaterial = materialRepository.findById(idOfMaterial).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Material with id=" + idOfMaterial + " not found")
        ));
        dbMaterial.setBest(true);

        materialRepository.save(dbMaterial);
    }

    @Override
    public List<DbMaterialRequest> getAllMaterialRequests() {
        return materialRequestRepository.findAllRequests(RequestStatus.Unchecked);
    }
}
