package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
