package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.ReportRepository;
import com.ifelseelif.blsslab1.Models.DTO.Report;
import com.ifelseelif.blsslab1.Models.DTO.ReviewedReport;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IReportService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;
    private final MaterialService materialService;


    public ReportService(ReportRepository reportRepository, MaterialService materialService) {
        this.reportRepository = reportRepository;
        this.materialService = materialService;
    }

    @Override
    public void send(Report report) {
        DbReport dbReport = new DbReport();
        dbReport.setMaterial(new DbMaterial(report.getMaterialId()));
        dbReport.setText(report.getText());
        this.reportRepository.save(dbReport);
    }

    @Override
    public String closeReport(ReviewedReport reviewedReport) {
        Optional<DbReport> dbReport = reportRepository.findById(reviewedReport.getReportId());

        if (dbReport.isPresent()) {

            reportRepository.deleteById(reviewedReport.getReportId());

            if (!reviewedReport.isMaterialGood()) {
                materialService.deleteMaterial(dbReport.get().getMaterial().getId());
            }

        } else {
            return "Report not found";
        }

        return "OK";
    }
}
