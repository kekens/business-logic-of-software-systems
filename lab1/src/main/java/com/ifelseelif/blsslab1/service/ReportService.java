package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.ReportRepository;
import com.ifelseelif.blsslab1.models.dto.Report;
import com.ifelseelif.blsslab1.models.domain.DbMaterial;
import com.ifelseelif.blsslab1.models.domain.DbReport;
import com.ifelseelif.blsslab1.service.interfaces.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;


    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void send(Report report) {
        DbReport dbReport = new DbReport();
        dbReport.setMaterial(new DbMaterial(report.getMaterialId()));
        dbReport.setText(report.getText());
        this.reportRepository.save(dbReport);
    }


}
