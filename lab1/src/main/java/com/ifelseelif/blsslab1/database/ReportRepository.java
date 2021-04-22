package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbReport;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<DbReport, Long> {

    DbReport findDbReportById(long id);
}
