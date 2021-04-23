package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DbUser, Long> {
    DbUser findByUsername(String username);
}
