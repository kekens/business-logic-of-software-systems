package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Blog;
import com.ifelseelif.blsslab1.Models.DTO.Review;
import com.ifelseelif.blsslab1.Models.DTO.Story;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private IMaterialService materialService;

    public MaterialController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/create/blog")
    public void createMaterial(Blog blog) {
        materialService.createBlog(blog);
    }

    @PostMapping("/create/review")
    public void createMaterial(Review review) {
        materialService.createReview(review);
    }

    @PostMapping("/create/story")
    public void createMaterial(Story story) {
        materialService.createStory(story);
    }

    @PutMapping("/update/blog/{id}")
    public void updateMaterial(Blog blog, @PathVariable int id) {
        materialService.updateBlog(id, blog);
    }

    @PutMapping("/update/review/{id}")
    public void updateMaterial(Review review, @PathVariable int id) {
        materialService.updateReview(id, review);
    }

    @PutMapping("/update/story/{id}")
    public void updateMaterial(Story story, @PathVariable int id) {
        materialService.updateStory(id, story);
    }

    @PostMapping("/send/{id}")
    public void send(@PathVariable int id) {
        materialService.sendMaterial(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
    }
}
