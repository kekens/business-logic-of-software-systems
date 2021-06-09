package com.ifelseelif.responseservice.service;

import com.ifelseelif.responseservice.database.MaterialRepository;
import com.ifelseelif.responseservice.database.NotificationRepository;
import com.ifelseelif.responseservice.models.domain.MaterialRequest;
import com.ifelseelif.responseservice.models.dto.ModeratorAnswer;
import com.ifelseelif.responseservice.models.dto.NotificationDto;
import com.ifelseelif.responseservice.models.dto.RequestStatus;
import com.ifelseelif.responseservice.models.dto.Status;
import com.ifelseelif.responseservice.service.interfaces.IResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ResponseService implements IResponseService {

    private final MaterialRepository materialRepository;
    private final NotificationService notificationService;

    @Autowired
    public ResponseService(MaterialRepository materialRepository, NotificationService notificationService) {
        this.materialRepository = materialRepository;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "tourist-messages")
    public void getModeratorAnswer(ModeratorAnswer moderatorAnswer) {
        MaterialRequest materialRequest = moderatorAnswer.getMaterialRequest();
        String notif = null;


        if (moderatorAnswer.getRequestStatus().equals(RequestStatus.Rejected)) {
            notif = String.format("-- Material request %d on material %d by user '%s' has been rejected by moderator: %s --",
                    moderatorAnswer.getMaterialRequest().getId(),
                    moderatorAnswer.getMaterialRequest().getMaterial().getId(),
                    moderatorAnswer.getMaterialRequest().getMaterial().getUser().getUsername(),
                    moderatorAnswer.getReason());

            log.info(notif);

            materialRepository.changeStatus(materialRequest.getMaterial().getId(), Status.Draft);
        } else if (moderatorAnswer.getRequestStatus().equals(RequestStatus.Approved)) {
            notif = String.format("-- Material request %d on material %d by user '%s' has been approved by moderator --",
                    moderatorAnswer.getMaterialRequest().getId(),
                    moderatorAnswer.getMaterialRequest().getMaterial().getId(),
                    moderatorAnswer.getMaterialRequest().getMaterial().getUser().getUsername());

            log.info(notif);
        }

        notificationService.saveNotification(new NotificationDto(notif, moderatorAnswer.getMaterialRequest().getMaterial().getUser()));
    }

}
