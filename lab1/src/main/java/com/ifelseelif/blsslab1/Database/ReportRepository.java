package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<DbReport, Long> {
}
