package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.ReportRepository;
import com.ifelseelif.blsslab1.Models.DTO.ReportResponse;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModeratorService implements IModeratorService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll();
    }
}
