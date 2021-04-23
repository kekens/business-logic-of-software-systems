package com.ifelseelif.blsslab1.database;

import com.ifelseelif.blsslab1.models.domain.DbRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<DbRole, Long> {
    DbRole findByName(String name);
}
