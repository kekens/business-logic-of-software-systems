package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbBlog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<DbBlog, Long> {
    @Query(value = "SELECT id FROM db_blog ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastBlog();
}
