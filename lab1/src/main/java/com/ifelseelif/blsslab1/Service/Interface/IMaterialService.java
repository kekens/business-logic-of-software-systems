package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.*;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import javassist.NotFoundException;

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
