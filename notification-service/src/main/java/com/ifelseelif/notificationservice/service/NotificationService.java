package com.ifelseelif.notificationservice.service;

import com.ifelseelif.notificationservice.database.NotificationRepository;
import com.ifelseelif.notificationservice.models.domain.Notification;
import com.ifelseelif.notificationservice.models.dto.NotificationDto;
import com.ifelseelif.notificationservice.service.interfaces.INotificationService;
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