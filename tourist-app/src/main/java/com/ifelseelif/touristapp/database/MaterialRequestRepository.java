package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.dto.RequestStatus;
import com.ifelseelif.touristapp.models.domain.Material;
import com.ifelseelif.touristapp.models.domain.MaterialRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MaterialRequestRepository extends CrudRepository<MaterialRequest, Long> {

    @Query("UPDATE MaterialRequest SET requestStatus=:requestStatus WHERE id=:id")
    @Modifying
    @Transactional
    void changeRequestStatus(long id, RequestStatus requestStatus);

    MaterialRequest findMaterialRequestByMaterial(Material material);

    @Query(value = "SELECT mr FROM MaterialRequest mr WHERE mr.requestStatus = :requestStatus")
    List<MaterialRequest> findAllRequests(RequestStatus requestStatus);

}
