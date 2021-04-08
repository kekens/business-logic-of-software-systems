package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.*;
import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.DTO.Status;
import com.ifelseelif.blsslab1.Models.DTO.StoryResponse;
import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
import com.ifelseelif.blsslab1.Models.Domain.*;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
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

    @Autowired
    public ModeratorService(ReportRepository reportRepository, CountryRepository countryRepository,
                            HotelRepository hotelRepository, StoryRepository storyRepository, MaterialRepository materialRepository) {
        this.reportRepository = reportRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.storyRepository = storyRepository;
        this.materialRepository = materialRepository;
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
    public String setVerifiedStory(long id) {
        Optional<DbStory> dbStory = storyRepository.findById(id);

        if (dbStory.isPresent()) {

            Optional<DbMaterial> dbMaterial = Optional.ofNullable(materialRepository.findDbMaterialByStory(dbStory.get()));

            if (dbMaterial.isPresent()) {
                if (!dbMaterial.get().getStatus().equals(Status.Published)) {
                    return "Story isn't published";
                }
            }

            if (dbStory.get().isVerified()) {
                return "Story has already been verified";
            }


        } else {
            return "Story not found";
        }

        storyRepository.setVerifiedStory(id);
        return "OK";
    }

    @Override
    public String publishMaterial(long id) {
        Optional<DbMaterial> dbMaterial = materialRepository.findById(id);

        if (dbMaterial.isPresent()) {
            if (dbMaterial.get().getTypeMaterial().equals(TypeMaterial.Story)) {
                return "Incorrect type of material";
            }

            if (!dbMaterial.get().getStatus().equals(Status.Approving)) {
                return "Incorrect status of material";
            }
        } else {
            return "Material not found";
        }

        materialRepository.changeStatus(id, Status.Published);
        return "OK";
    }

    @Override
    public void selectBestMaterial(long idOfMaterial) {
        DbMaterial dbMaterial = materialRepository.findById(idOfMaterial).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Material with id=" + idOfMaterial + " not found")
        ));
        dbMaterial.setBest(true);

        materialRepository.save(dbMaterial);
    }
}
