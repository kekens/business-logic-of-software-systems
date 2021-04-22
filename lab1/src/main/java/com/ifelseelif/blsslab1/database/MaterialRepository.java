package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.dto.Status;
import com.ifelseelif.blsslab1.models.domain.DbBlog;
import com.ifelseelif.blsslab1.models.domain.DbMaterial;
import com.ifelseelif.blsslab1.models.domain.DbReview;
import com.ifelseelif.blsslab1.models.domain.DbStory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MaterialRepository extends CrudRepository<DbMaterial, Long> {
    DbMaterial getDbMaterialById(long id);

    @Query("UPDATE DbMaterial SET status=:status WHERE id=:id")
    @Modifying
    @Transactional
    void changeStatus(long id, Status status);

    @Query(value = "SELECT * FROM db_material WHERE is_best=true", nativeQuery = true)
    List<DbMaterial> getAllBestMaterials();

    DbMaterial findDbMaterialByStory(DbStory dbStory);
    DbMaterial findDbMaterialByBlog(DbBlog dbBlog);
    DbMaterial findDbMaterialByReview(DbReview dbReview);
}
