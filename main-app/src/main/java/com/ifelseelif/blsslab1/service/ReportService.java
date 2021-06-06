package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.ReportRepository;
import com.ifelseelif.blsslab1.models.dto.ReportDto;
import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.domain.Report;
import com.ifelseelif.blsslab1.service.interfaces.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;


    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void send(ReportDto reportDto) {
        Report report = new Report();
        report.setMaterial(new Material(reportDto.getMaterialId()));
        report.setText(reportDto.getText());
        this.reportRepository.save(report);
    }


}
