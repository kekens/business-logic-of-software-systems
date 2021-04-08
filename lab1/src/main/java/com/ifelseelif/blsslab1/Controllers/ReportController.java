package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Report;
import com.ifelseelif.blsslab1.Models.DTO.ReviewedReport;
import com.ifelseelif.blsslab1.Service.Interface.IReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void createMaterial(@RequestBody Report report) {
        reportService.send(report);
    }

    @PostMapping("/close")
    public ResponseEntity<String> closeReport(@RequestBody ReviewedReport reviewedReport) {
        String response = reportService.closeReport(reviewedReport);

        if (response.equals("OK")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
