package com.ifelseelif.blsslab1.controllers;

import com.ifelseelif.blsslab1.models.dto.ReportDto;
import com.ifelseelif.blsslab1.service.interfaces.IReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void createMaterial(@RequestBody ReportDto reportDto) {
        reportService.send(reportDto);
    }


}
