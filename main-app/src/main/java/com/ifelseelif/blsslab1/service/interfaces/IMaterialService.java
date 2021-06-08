package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.dto.*;
import com.ifelseelif.blsslab1.models.domain.Material;

import java.util.List;

public interface IMaterialService {

    List<Material> getAllMaterials(String username);

    Material getMaterial(long id);

    void deleteMaterial(long id);

    void createMaterial(TypeMaterial typeMaterial, String username);

    void createBlog(BlogDto blogDto, String username);

    void createReview(ReviewDto reviewDto, String username);

    void createStory(StoryDto storyDto, String username);

    void updateBlog(long id, BlogDto blogDto);

    void updateReview(long id, ReviewDto reviewDto);

    void updateStory(long id, StoryDto storyDto);

    void sendMaterial(long id);

    List<Material> getAllBestMaterials();
}
