package com.ifelseelif.blsslab1.Controllers;

import com.ifelseelif.blsslab1.Models.DTO.Material;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private IMaterialService materialService;

    public MaterialController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/create")
    public void createMaterial(Material material) {
        materialService.createMaterial(material);
    }

    @PostMapping("/publish/{id}")
    public void publishMaterial(@PathVariable int id) {
        materialService.publishMaterial(id);
    }

    @PutMapping("/{id}")
    public void updateMaterial(Material material, @PathVariable int id) {
        materialService.updateMaterial(id, material);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
    }
}
