package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbBlog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<DbBlog, Long> {
    @Query(value = "SELECT id FROM db_blog ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastBlog();
}
