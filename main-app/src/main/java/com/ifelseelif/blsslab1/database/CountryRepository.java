package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
