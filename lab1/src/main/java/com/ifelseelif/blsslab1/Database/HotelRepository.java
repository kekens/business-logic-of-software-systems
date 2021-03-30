package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbHotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<DbHotel, Long> {
}
