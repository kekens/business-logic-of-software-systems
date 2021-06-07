package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.dto.Status;
import com.ifelseelif.blsslab1.models.domain.Blog;
import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.domain.Review;
import com.ifelseelif.blsslab1.models.domain.Story;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MaterialRepository extends CrudRepository<Material, Long> {
    Material getMaterialById(long id);

    @Query("UPDATE Material SET status=:status WHERE id=:id")
    @Modifying
    @Transactional
    void changeStatus(long id, Status status);

    @Query(value = "SELECT * FROM material WHERE is_best=true", nativeQuery = true)
    List<Material> getAllBestMaterials();

    Material findMaterialByStory(Story story);
    Material findMaterialByBlog(Blog blog);
    Material findMaterialByReview(Review review);
}
