package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbStory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StoryRepository extends CrudRepository<DbStory, Long> {
    @Query(value = "SELECT id FROM db_story ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastStory();
}
