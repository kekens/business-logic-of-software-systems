package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface MaterialRepository extends CrudRepository<DbMaterial, Long> {
    DbMaterial getDbMaterialById(long id);

    @Query("UPDATE DbMaterial SET status=com.ifelseelif.blsslab1.Models.DTO.Status.Approving WHERE id=:id")
    @Modifying
    @Transactional
    void sendOnApproving(long id);
}
