package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbStory;
import org.springframework.data.repository.CrudRepository;

public interface StoryRepository extends CrudRepository<DbStory, Long> {
}
