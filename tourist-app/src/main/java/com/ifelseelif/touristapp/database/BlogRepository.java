package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    @Query(value = "SELECT id FROM blog ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastBlog();
}
