package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbHotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<DbHotel, Long> {
}
