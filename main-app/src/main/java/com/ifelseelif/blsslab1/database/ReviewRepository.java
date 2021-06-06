package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Query(value = "SELECT id FROM review ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastReview();
}
