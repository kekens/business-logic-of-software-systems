package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
