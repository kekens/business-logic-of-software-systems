package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<DbMaterial, Long> {
    DbMaterial getDbMaterialById(long id);
}
