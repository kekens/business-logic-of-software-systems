package com.ifelseelif.responseservice.service.interfaces;

import com.ifelseelif.responseservice.models.domain.Notification;
import com.ifelseelif.responseservice.models.dto.NotificationDto;

public interface INotificationService {

    void saveNotification(NotificationDto notificationDto);

}
