package com.ifelseelif.responseservice.database;

import com.ifelseelif.responseservice.models.domain.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
