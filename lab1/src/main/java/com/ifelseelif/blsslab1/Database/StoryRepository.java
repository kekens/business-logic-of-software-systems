package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbStory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface StoryRepository extends CrudRepository<DbStory, Long> {
    @Query(value = "SELECT id FROM db_story ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastStory();

    @Query(value = "SELECT * FROM db_story INNER JOIN db_material ON db_story.id = db_material.story_id " +
            "WHERE is_verified=false AND db_material.status<>'Draft'", nativeQuery = true)
    List<DbStory> findAllUnverifiedStories();

    @Query("UPDATE DbStory SET isVerified=true WHERE id=:id")
    @Modifying
    @Transactional
    void setVerifiedStory(long id);
}
