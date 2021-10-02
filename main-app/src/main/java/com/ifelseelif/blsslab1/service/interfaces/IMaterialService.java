package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.domain.publications.PublicationStrategy;
import com.ifelseelif.blsslab1.models.dto.*;
import com.ifelseelif.blsslab1.models.domain.Material;

import java.util.List;

public interface IMaterialService {

    List<Material> getAllMaterials();

    Material getMaterial(long id);

    void deleteMaterial(long id);

    void createMaterial(PublicationStrategy typeMaterial);

    void createBlog(BlogDto blogDto);

    void createReview(ReviewDto reviewDto);

    void createStory(StoryDto storyDto);

    void updateBlog(long id, BlogDto blogDto);

    void updateReview(long id, ReviewDto reviewDto);

    void updateStory(long id, StoryDto storyDto);

    void sendMaterial(long id);

    List<Material> getAllBestMaterials();
}
