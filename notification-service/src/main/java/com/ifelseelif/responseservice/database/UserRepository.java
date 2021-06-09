package com.ifelseelif.responseservice.database;

import com.ifelseelif.responseservice.models.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
