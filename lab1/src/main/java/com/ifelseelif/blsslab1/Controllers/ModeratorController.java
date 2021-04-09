package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.DTO.ReviewedReport;
import com.ifelseelif.blsslab1.Models.DTO.StoryResponse;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterialRequest;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
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
    public ResponseEntity<String> rejectMaterial(long id)
    {
        try {
            moderatorService.rejectMaterial(id);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/requests/all")
    public List<DbMaterialRequest> getAllMaterialRequests() {
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
    public List<DbReport> getAllReports() {
        return moderatorService.getAllReports();
    }

    @GetMapping("/reports/{id}")
    public DbReport getReport(@PathVariable long id) {
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
    public void addHotel(@RequestBody Hotel hotel) {
        moderatorService.addHotel(hotel);
    }

    @PostMapping("/selectBest/{idOfMaterial}")
    public void selectBestMaterial(@PathVariable long idOfMaterial) {
        moderatorService.selectBestMaterial(idOfMaterial);
    }
}
