package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Report;
import com.ifelseelif.blsslab1.Service.Interface.IReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/send")
    public void createMaterial(Report report) {
        reportService.send(report);
    }
}
