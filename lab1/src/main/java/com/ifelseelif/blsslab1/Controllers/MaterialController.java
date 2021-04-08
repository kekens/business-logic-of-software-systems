package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Blog;
import com.ifelseelif.blsslab1.Models.DTO.Review;
import com.ifelseelif.blsslab1.Models.DTO.Story;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import com.ifelseelif.blsslab1.Service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private final IMaterialService materialService;

    public MaterialController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/all")
    public List<DbMaterial> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public DbMaterial getMaterial(@PathVariable long id) {
        return materialService.getMaterial(id);
    }

    @PostMapping("/create/blog")
    public void createMaterial(@RequestBody Blog blog) {
        materialService.createBlog(blog);
    }

    @PostMapping("/create/review")
    public void createMaterial(@RequestBody Review review) {
        materialService.createReview(review);
    }

    @PostMapping("/create/story")
    public void createMaterial(@RequestBody Story story) {
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

    @PostMapping("/send")
    public void send(long id) {
        materialService.sendMaterial(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
    }
}
