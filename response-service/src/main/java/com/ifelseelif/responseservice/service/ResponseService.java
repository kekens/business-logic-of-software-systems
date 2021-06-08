package com.ifelseelif.responseservice.service;

import com.ifelseelif.responseservice.database.MaterialRepository;
import com.ifelseelif.responseservice.models.domain.MaterialRequest;
import com.ifelseelif.responseservice.models.dto.ModeratorAnswer;
import com.ifelseelif.responseservice.models.dto.RequestStatus;
import com.ifelseelif.responseservice.models.dto.Status;
import com.ifelseelif.responseservice.service.interfaces.IResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseService implements IResponseService {

    private final MaterialRepository materialRepository;

    @Autowired
    public ResponseService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @KafkaListener(topics = "tourist-messages")
    public void getModeratorAnswer(ModeratorAnswer moderatorAnswer) {
        MaterialRequest materialRequest = moderatorAnswer.getMaterialRequest();

        if (moderatorAnswer.getRequestStatus().equals(RequestStatus.Rejected)) {
            log.info("Material request " + moderatorAnswer.getMaterialRequest().getId() + " " +
                    "on material " + moderatorAnswer.getMaterialRequest().getMaterial().getId() +
                    " has been rejected by moderator: " + moderatorAnswer.getReason());

            materialRepository.changeStatus(materialRequest.getMaterial().getId(), Status.Draft);
        } else if (moderatorAnswer.getRequestStatus().equals(RequestStatus.Approved)) {
            log.info("Material request " + moderatorAnswer.getMaterialRequest().getId() + " " +
                    "on material " + moderatorAnswer.getMaterialRequest().getMaterial().getId() +
                    " has been approved");
        }
    }

}
