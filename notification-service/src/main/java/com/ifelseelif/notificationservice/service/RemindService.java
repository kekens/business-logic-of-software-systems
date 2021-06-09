package com.ifelseelif.notificationservice.service;

import com.ifelseelif.notificationservice.database.MaterialRequestRepository;
import com.ifelseelif.notificationservice.database.UserRepository;
import com.ifelseelif.notificationservice.models.domain.MaterialRequest;
import com.ifelseelif.notificationservice.models.domain.Notification;
import com.ifelseelif.notificationservice.models.domain.Role;
import com.ifelseelif.notificationservice.models.domain.User;
import com.ifelseelif.notificationservice.models.dto.NotificationDto;
import com.ifelseelif.notificationservice.models.dto.RequestStatus;
import com.ifelseelif.notificationservice.service.interfaces.IRemindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RemindService implements IRemindService {

    private final MaterialRequestRepository materialRequestRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Autowired
    public RemindService(MaterialRequestRepository materialRequestRepository, NotificationService notificationService, UserRepository userRepository) {
        this.materialRequestRepository = materialRequestRepository;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    @Override
    public void remindModer() {
        List<MaterialRequest> materialRequestsList = materialRequestRepository.findAllRequests(RequestStatus.Unchecked);
        String notif = String.format("You have %d unchecked requests", materialRequestsList.size());
        log.info("-----------------");
        log.info("New notification:");
        log.info("=================");
        log.info(notif);
        log.info("=================");

        List<User> modersList = userRepository.findAllUsersWithRole(new Role(2L, "ROLE_MODERATOR"));

        for (User user : modersList) {
            notificationService.saveNotification(new NotificationDto(notif, user));
        }
    }
}
