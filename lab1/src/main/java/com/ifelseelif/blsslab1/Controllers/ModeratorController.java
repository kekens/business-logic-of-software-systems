package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
import com.ifelseelif.blsslab1.Service.ModeratorService;
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

    @GetMapping("/reports/all")
    public List<DbReport> getAllReports() {
        return moderatorService.getAllReports();
    }

    @GetMapping("/reports/{id}")
    public DbReport getReport(@PathVariable int id) {
        return moderatorService.getReport(id);
    }

    @PostMapping("/add/country")
    public void addCountry(String name) {
        this.moderatorService.addCountry(name);
    }

    @PostMapping("/add/hotel")
    public void addHotel(@RequestBody Hotel hotel) {
        this.moderatorService.addHotel(hotel);
    }

}
