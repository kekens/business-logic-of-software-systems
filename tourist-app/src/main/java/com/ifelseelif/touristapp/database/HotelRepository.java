package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
