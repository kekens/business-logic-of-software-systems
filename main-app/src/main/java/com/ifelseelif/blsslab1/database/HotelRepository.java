package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
