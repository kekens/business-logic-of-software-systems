package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.database.RoleRepository;
import com.ifelseelif.touristapp.database.UserRepository;
import com.ifelseelif.touristapp.models.domain.Role;
import com.ifelseelif.touristapp.models.domain.User;
import com.ifelseelif.touristapp.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleEntityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleEntityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User u) {
        Role role = roleEntityRepository.findByName("ROLE_USER");
        u.setRoles(Collections.singleton(role));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(u);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if ((userEntity != null) && (passwordEncoder.matches(password, userEntity.getPassword()))) {
            return userEntity;
        }

        return null;
    }
}
