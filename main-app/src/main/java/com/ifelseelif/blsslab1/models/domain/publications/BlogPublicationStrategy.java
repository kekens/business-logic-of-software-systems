package com.ifelseelif.blsslab1.models.domain.publications;

import com.ifelseelif.blsslab1.database.BlogRepository;
import com.ifelseelif.blsslab1.models.domain.Blog;
import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.dto.TypeMaterial;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogPublicationStrategy implements PublicationStrategy {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public TypeMaterial getTypeMaterial() {
        return TypeMaterial.Blog;
    }

    @Override
    public void setPublicationInMaterial(Material material) {
        material.setBlog(new Blog(blogRepository.findLastBlog()));
    }
}
