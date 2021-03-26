package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.ReportResponse;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    private final IModeratorService moderatorService;

    public ModeratorController(IModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @GetMapping("/reports/all")
    public List<ReportResponse> getAllReports() {
        return moderatorService.getAllReports();
    }
}
