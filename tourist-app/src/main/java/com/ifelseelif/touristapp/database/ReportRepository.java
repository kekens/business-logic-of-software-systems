package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findReportById(long id);
}
