package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.database.NotificationRepository;
import com.ifelseelif.touristapp.database.UserRepository;
import com.ifelseelif.touristapp.models.domain.Notification;
import com.ifelseelif.touristapp.security.CustomUserDetails;
import com.ifelseelif.touristapp.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> getAllNotifications() {
        CustomUserDetails principal = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return notificationRepository.findAllByUser(userRepository.findByUsername(principal.getUsername()));
    }

}