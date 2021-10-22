package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.Notification;
import com.ifelseelif.touristapp.models.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findAllByUser(User user);

}
