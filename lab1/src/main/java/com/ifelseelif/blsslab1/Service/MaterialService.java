package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.CountryRepository;
import com.ifelseelif.blsslab1.Database.MaterialRepository;
import com.ifelseelif.blsslab1.Database.StoryRepository;
import com.ifelseelif.blsslab1.Models.DTO.Material;
import com.ifelseelif.blsslab1.Models.Domain.DbCountry;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbStory;
import com.ifelseelif.blsslab1.Service.Interface.IMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public void createMaterial(Material material) {
        DbCountry country = new DbCountry();
        country.setName("123");
        countryRepository.save(country);
        logger.info("create material");
        DbStory dbStory = new DbStory();
        dbStory.setHeader("12");
        Optional<DbCountry> country1 = countryRepository.findById(2L);

        Optional<DbCountry> country2 = countryRepository.findById(3L);
        HashSet<DbCountry> countries = new HashSet<>();
        countries.add(country1.get());
        countries.add(country2.get());
        dbStory.setCountrySet(countries);
        storyRepository.save(dbStory);
    /*    DbMaterial dbMaterial = new DbMaterial("1");
        materialRepository.save(dbMaterial);
    */
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
