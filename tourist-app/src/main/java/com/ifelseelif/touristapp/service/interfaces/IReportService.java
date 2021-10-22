package com.ifelseelif.touristapp.service.interfaces;

import com.ifelseelif.touristapp.models.dto.ReportDto;

public interface IReportService {
    void send(ReportDto reportDto);
}
