package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<DbReview, Long> {
    @Query(value = "SELECT id FROM db_review ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastReview();
}
