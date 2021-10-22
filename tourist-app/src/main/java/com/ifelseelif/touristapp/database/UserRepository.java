package com.ifelseelif.touristapp.database;

import com.ifelseelif.touristapp.models.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
