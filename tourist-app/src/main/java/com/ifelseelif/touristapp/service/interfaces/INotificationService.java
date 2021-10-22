package com.ifelseelif.touristapp.service.interfaces;


import com.ifelseelif.touristapp.models.domain.Notification;

import java.util.List;

public interface INotificationService {

    List<Notification> getAllNotifications();

}
