package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.DTO.Status;
import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbStory;
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

    @Query(value = "SELECT type_material FROM db_material WHERE id=:id", nativeQuery = true)
    TypeMaterial findTypeMaterialById(long id);

    @Query(value = "SELECT * FROM db_material WHERE is_best=true", nativeQuery = true)
    List<DbMaterial> getAllMaterials();

    DbMaterial findDbMaterialByStory(DbStory id);
}
