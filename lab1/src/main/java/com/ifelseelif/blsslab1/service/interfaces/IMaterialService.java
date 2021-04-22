package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.dto.*;
import com.ifelseelif.blsslab1.models.domain.DbMaterial;

import java.util.List;

public interface IMaterialService {

    List<DbMaterial> getAllMaterials();

    DbMaterial getMaterial(long id);

    void deleteMaterial(long id);

    void createMaterial(TypeMaterial typeMaterial);

    void createBlog(Blog blog);

    void createReview(Review review);

    void createStory(Story story);

    void updateBlog(long id, Blog blog);

    void updateReview(long id, Review review);

    void updateStory(long id, Story story);

    void sendMaterial(long id);

    List<DbMaterial> getAllBestMaterials();
}
