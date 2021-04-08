package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.*;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;

import java.util.List;

public interface IMaterialService {

    void publishMaterial(int id, TypeMaterial typeMaterial);

    List<DbMaterial> getAllMaterials();
    DbMaterial getMaterial(long id);

    void deleteMaterial(long id);

//    void updateMaterial(int id, Material material);

    void createMaterial(TypeMaterial typeMaterial);

    void createBlog(Blog blog);

    void createReview(Review review);

    void createStory(Story story);

    void updateBlog(long id, Blog blog);

    void updateReview(long id, Review review);

    void updateStory(long id, Story story);

    void sendMaterial(long id);
}
