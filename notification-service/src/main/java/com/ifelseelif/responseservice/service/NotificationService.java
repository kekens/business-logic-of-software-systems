package com.ifelseelif.responseservice.service;

import com.ifelseelif.responseservice.database.NotificationRepository;
import com.ifelseelif.responseservice.models.domain.Notification;
import com.ifelseelif.responseservice.models.dto.NotificationDto;
import com.ifelseelif.responseservice.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void saveNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();

        notification.setMessage(notificationDto.getMessage());
        notification.setUser(notificationDto.getUser());

        notificationRepository.save(notification);
    }

}