package com.ifelseelif.notificationservice.database;

import com.ifelseelif.notificationservice.models.domain.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
