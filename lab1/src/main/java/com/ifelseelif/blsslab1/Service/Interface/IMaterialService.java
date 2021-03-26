package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.*;

public interface IMaterialService {

    void publishMaterial(int id, TypeMaterial typeMaterial);

    void deleteMaterial(int id);

    void updateMaterial(int id, Material material);

    void createBlog(Blog blog);

    void createReview(Review review);

    void createStory(Story story);

    void updateBlog(int id, Blog blog);

    void updateReview(int id, Review review);

    void updateStory(int id, Story story);

    void sendMaterial(int id);
}
