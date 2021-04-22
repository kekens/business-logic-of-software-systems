package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbCountry;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<DbCountry, Long> {
}
