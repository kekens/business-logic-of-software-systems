package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Story;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface StoryRepository extends CrudRepository<Story, Long> {
    @Query(value = "SELECT id FROM story ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    long findLastStory();

    @Query(value = "SELECT * FROM story INNER JOIN material ON story.id = material.story_id " +
            "WHERE is_verified=false AND material.status<>'Draft'", nativeQuery = true)
    List<Story> findAllUnverifiedStories();

    @Query("UPDATE Story SET isVerified=true WHERE id=:id")
    @Modifying
    @Transactional
    void setVerifiedStory(long id);
}
