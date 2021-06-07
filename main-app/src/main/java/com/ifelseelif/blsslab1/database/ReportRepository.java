package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findReportById(long id);
}
