package com.ifelseelif.blsslab1.service.interfaces;


import com.ifelseelif.blsslab1.models.domain.Notification;
import com.ifelseelif.blsslab1.models.dto.NotificationDto;

import java.util.List;

public interface INotificationService {

    List<Notification> getAllNotifications();

}
