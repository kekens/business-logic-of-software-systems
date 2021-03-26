package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.ReportRepository;
import com.ifelseelif.blsslab1.Models.DTO.Report;
import com.ifelseelif.blsslab1.Service.Interface.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void send(Report report) {

    }
}
