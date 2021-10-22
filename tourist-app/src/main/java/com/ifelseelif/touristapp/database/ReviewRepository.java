package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Query(value = "SELECT id FROM review ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastReview();
}
