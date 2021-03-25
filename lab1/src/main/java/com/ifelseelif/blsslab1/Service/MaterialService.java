package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.MaterialRepository;
import com.ifelseelif.blsslab1.Models.DTO.Material;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class MaterialService implements IMaterialService {
    private Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    private MaterialRepository materialRepository;

    public MaterialService(){}

    @Override
    public void createMaterial(Material material) {
        logger.info("create material");
        DbMaterial dbMaterial = new DbMaterial();
        materialRepository.save(dbMaterial);
    }

    @Override
    public void publishMaterial(int id) {
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
}
