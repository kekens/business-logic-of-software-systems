package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.Material;

public interface IMaterialService {
    void createMaterial(Material material);

    void publishMaterial(int id);

    void deleteMaterial(int id);

    void updateMaterial(int id, Material material);
}
