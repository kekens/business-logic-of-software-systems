package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.database.ReportRepository;
import com.ifelseelif.touristapp.models.dto.ReportDto;
import com.ifelseelif.touristapp.models.domain.Material;
import com.ifelseelif.touristapp.models.domain.Report;
import com.ifelseelif.touristapp.service.interfaces.IReportService;
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
