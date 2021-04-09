package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.ReportRepository;
import com.ifelseelif.blsslab1.Models.DTO.Report;
import com.ifelseelif.blsslab1.Models.DTO.ReviewedReport;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IReportService;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
