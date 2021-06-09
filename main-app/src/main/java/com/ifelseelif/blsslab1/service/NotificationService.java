package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.NotificationRepository;
import com.ifelseelif.blsslab1.database.UserRepository;
import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.domain.Notification;
import com.ifelseelif.blsslab1.models.dto.NotificationDto;
import com.ifelseelif.blsslab1.security.CustomUserDetails;
import com.ifelseelif.blsslab1.service.interfaces.INotificationService;
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