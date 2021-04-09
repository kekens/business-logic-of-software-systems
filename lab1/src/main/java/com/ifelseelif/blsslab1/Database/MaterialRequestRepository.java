package com.ifelseelif.blsslab1.Database;

import com.ifelseelif.blsslab1.Models.DTO.RequestStatus;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterialRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MaterialRequestRepository extends CrudRepository<DbMaterialRequest, Long> {

    @Query("UPDATE DbMaterialRequest SET requestStatus=:requestStatus WHERE id=:id")
    @Modifying
    @Transactional
    void changeRequestStatus(long id, RequestStatus requestStatus);

    DbMaterialRequest findDbMaterialRequestByDbMaterial(DbMaterial dbMaterial);

    @Query(value = "SELECT mr FROM DbMaterialRequest mr WHERE mr.requestStatus = :requestStatus")
    List<DbMaterialRequest> findAllRequests(RequestStatus requestStatus);

}
