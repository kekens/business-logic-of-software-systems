package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.CountryRepository;
import com.ifelseelif.blsslab1.Database.MaterialRepository;
import com.ifelseelif.blsslab1.Database.StoryRepository;
import com.ifelseelif.blsslab1.Models.DTO.*;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class MaterialService implements IMaterialService {
    private Logger logger = LoggerFactory.getLogger(MaterialService.class);

    private final MaterialRepository materialRepository;

    private final StoryRepository storyRepository;

    private final CountryRepository countryRepository;

    public MaterialService(MaterialRepository materialRepository, StoryRepository storyRepository, CountryRepository countryRepository) {
        this.materialRepository = materialRepository;
        this.storyRepository = storyRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void publishMaterial(int id, TypeMaterial typeMaterial) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteMaterial(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void updateMaterial(int id, Material material) {
        throw new NotImplementedException();
    }

    @Override
    public void createBlog(Blog blog) {

    }

    @Override
    public void createReview(Review review) {

    }

    @Override
    public void createStory(Story story) {

    }

    @Override
    public void updateBlog(int id, Blog blog) {

    }

    @Override
    public void updateReview(int id, Review review) {

    }

    @Override
    public void updateStory(int id, Story story) {

    }

    @Override
    public void sendMaterial(int id) {

    }
}
