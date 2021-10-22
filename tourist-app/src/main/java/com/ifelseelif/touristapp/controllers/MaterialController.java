package com.ifelseelif.touristapp.controllers;

import com.ifelseelif.touristapp.models.dto.BlogDto;
import com.ifelseelif.touristapp.models.dto.ReviewDto;
import com.ifelseelif.touristapp.models.dto.StoryDto;
import com.ifelseelif.touristapp.models.domain.Material;
import com.ifelseelif.touristapp.service.interfaces.IMaterialService;
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
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/all/best")
    public List<Material> getAllBestMaterials() {
        return materialService.getAllBestMaterials();
    }

    @GetMapping("/{id}")
    public Material getMaterial(@PathVariable long id) {
        return materialService.getMaterial(id);
    }

    @PostMapping("/create/blog")
    public void createMaterial(@RequestBody BlogDto blogDto) {
        materialService.createBlog(blogDto);
    }

    @PostMapping("/create/review")
    public void createMaterial(@RequestBody ReviewDto reviewDto) {
        materialService.createReview(reviewDto);
    }

    @PostMapping("/create/story")
    public void createMaterial(@RequestBody StoryDto storyDto) {
        materialService.createStory(storyDto);
    }

    @PutMapping("/update/blog/{id}")
    public  ResponseEntity<String> updateMaterial(@RequestBody BlogDto blogDto, @PathVariable int id) {
        try {
            materialService.updateBlog(id, blogDto);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/review/{id}")
    public ResponseEntity<String> updateMaterial(@RequestBody ReviewDto reviewDto, @PathVariable int id) {
        try {
            materialService.updateReview(id, reviewDto);
        } catch (ResponseStatusException r) {
            return ResponseEntity.status(r.getStatus()).body(r.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/story/{id}")
    public  ResponseEntity<String> updateMaterial(@RequestBody StoryDto storyDto, @PathVariable int id) {
        try {
            materialService.updateStory(id, storyDto);
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
