package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.*;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;

import java.util.List;

public interface IMaterialService {

    void publishMaterial(int id, TypeMaterial typeMaterial);

    List<DbMaterial> getAllMaterials();
    DbMaterial getMaterial(long id);

    void deleteMaterial(int id);

//    void updateMaterial(int id, Material material);

    void createMaterial(TypeMaterial typeMaterial);

    void createBlog(Blog blog);

    void createReview(Review review);

    void createStory(Story story);

    void updateBlog(int id, Blog blog);

    void updateReview(int id, Review review);

    void updateStory(int id, Story story);

    void sendMaterial(int id);
}
