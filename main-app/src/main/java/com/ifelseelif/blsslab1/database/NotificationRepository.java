package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Notification;
import com.ifelseelif.blsslab1.models.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findAllByUser(User user);

}
