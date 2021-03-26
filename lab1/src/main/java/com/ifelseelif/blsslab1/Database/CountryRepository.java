package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbCountry;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<DbCountry, Long> {
}
