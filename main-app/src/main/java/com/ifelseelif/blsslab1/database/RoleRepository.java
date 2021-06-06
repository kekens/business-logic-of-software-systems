package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
