package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.RoleRepository;
import com.ifelseelif.blsslab1.database.UserRepository;
import com.ifelseelif.blsslab1.models.domain.DbRole;
import com.ifelseelif.blsslab1.models.domain.DbUser;
import com.ifelseelif.blsslab1.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements IUserService {

    private final
    UserRepository userRepository;

    private final RoleRepository roleEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleEntityRepository) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
    }

    public DbUser findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(DbUser u) {
        DbRole role = roleEntityRepository.findByName("ROLE_USER");
        u.setRoles(Collections.singleton(role));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(u);
    }

    @Override
    public DbUser findByLoginAndPassword(String login, String password) {
        DbUser userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
