package com.ifelseelif.touristapp.controllers;

import com.ifelseelif.touristapp.models.dto.*;
import com.ifelseelif.touristapp.models.domain.MaterialRequest;
import com.ifelseelif.touristapp.models.domain.Report;
import com.ifelseelif.touristapp.service.interfaces.IModeratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    private final IModeratorService moderatorService;

    public ModeratorController(IModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping("/requests/publish")
    public ResponseEntity<String> publishMaterial(long id)
    {
        try {
            moderatorService.publishMaterial(id);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/requests/reject")
    public ResponseEntity<String> rejectMaterial(@RequestBody ModeratorRejectRequest moderatorRejectRequest)
    {
        try {
            moderatorService.rejectMaterial(moderatorRejectRequest);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/requests/all")
    public List<MaterialRequest> getAllMaterialRequests() {
        return moderatorService.getAllMaterialRequests();
    }

    @GetMapping("/stories/all")
    public List<StoryResponse> getUnverifiedStories() {
        return moderatorService.getUnverifiedStories();
    }

    @PostMapping("/stories/verify")
    public void setVerifiedStory(long id) {
        moderatorService.setVerifiedStory(id);
    }

    @GetMapping("/reports/all")
    public List<Report> getAllReports() {
        return moderatorService.getAllReports();
    }

    @GetMapping("/reports/{id}")
    public Report getReport(@PathVariable long id) {
        return moderatorService.getReport(id);
    }

    @PostMapping("/reports/close")
    public void closeReport(@RequestBody ReviewedReport reviewedReport) {
        moderatorService.closeReport(reviewedReport);
    }

    @PostMapping("/add/country")
    public void addCountry(String name) {
        moderatorService.addCountry(name);
    }

    @PostMapping("/add/hotel")
    public void addHotel(@RequestBody HotelDto hotelDto) {
        moderatorService.addHotel(hotelDto);
    }

    @PostMapping("/selectBest/{idOfMaterial}")
    public void selectBestMaterial(@PathVariable long idOfMaterial) {
        moderatorService.selectBestMaterial(idOfMaterial);
    }
}
