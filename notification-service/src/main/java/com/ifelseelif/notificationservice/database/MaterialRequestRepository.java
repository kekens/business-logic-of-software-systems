package com.ifelseelif.notificationservice.database;

import com.ifelseelif.notificationservice.models.domain.Material;
import com.ifelseelif.notificationservice.models.domain.MaterialRequest;
import com.ifelseelif.notificationservice.models.dto.RequestStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MaterialRequestRepository extends CrudRepository<MaterialRequest, Long> {

    @Query(value = "SELECT mr FROM MaterialRequest mr WHERE mr.requestStatus = :requestStatus")
    List<MaterialRequest> findAllRequests(RequestStatus requestStatus);

}
