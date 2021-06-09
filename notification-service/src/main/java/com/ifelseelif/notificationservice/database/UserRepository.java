package com.ifelseelif.notificationservice.database;

import com.ifelseelif.notificationservice.models.domain.Role;
import com.ifelseelif.notificationservice.models.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT user FROM User user WHERE :role member user.roles")
    List<User> findAllUsersWithRole(@Param("role") Role role);
}
