package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Blog;
import com.ifelseelif.blsslab1.Models.DTO.Review;
import com.ifelseelif.blsslab1.Models.DTO.Story;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/all/best")
    public List<DbMaterial> getAllBestMaterials() {
        return materialService.getAllBestMaterials();
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
    public  ResponseEntity<String> updateMaterial(@RequestBody Blog blog, @PathVariable int id) {
        try {
            materialService.updateBlog(id, blog);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/review/{id}")
    public ResponseEntity<String> updateMaterial(@RequestBody Review review, @PathVariable int id) {
        try {
            materialService.updateReview(id, review);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/story/{id}")
    public  ResponseEntity<String> updateMaterial(@RequestBody Story story, @PathVariable int id) {
        try {
            materialService.updateStory(id, story);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(long id) {
        try {
            materialService.sendMaterial(id);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
    }
}
