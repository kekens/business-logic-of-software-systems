package com.ifelseelif.blsslab1.models.domain.publications;

import com.ifelseelif.blsslab1.database.StoryRepository;
import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.domain.Story;
import com.ifelseelif.blsslab1.models.dto.TypeMaterial;
import org.springframework.beans.factory.annotation.Autowired;

public class StoryPublicationStrategy implements PublicationStrategy {
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public TypeMaterial getTypeMaterial() {
        return TypeMaterial.Story;
    }

    @Override
    public void setPublicationInMaterial(Material material) {
        material.setStory(new Story(storyRepository.findLastStory()));
    }
}
