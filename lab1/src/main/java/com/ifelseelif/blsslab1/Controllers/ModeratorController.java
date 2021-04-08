package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.DTO.StoryResponse;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    private final IModeratorService moderatorService;

    public ModeratorController(IModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping("/material/publish")
    public ResponseEntity<String> publishMaterial(long id) {
        String response = moderatorService.publishMaterial(id);

        if (response.equals("OK")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/stories/all")
    public List<StoryResponse> getUnverifiedStories() {
        return moderatorService.getUnverifiedStories();
    }

    @PostMapping("/stories/verify")
    public ResponseEntity<String> setVerifiedStory(long id) {
        String response = moderatorService.setVerifiedStory(id);

        if (response.equals("OK")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/reports/all")
    public List<DbReport> getAllReports() {
        return moderatorService.getAllReports();
    }

    @GetMapping("/reports/{id}")
    public DbReport getReport(@PathVariable long id) {
        return moderatorService.getReport(id);
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
