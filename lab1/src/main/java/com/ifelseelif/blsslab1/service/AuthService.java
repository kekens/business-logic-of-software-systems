package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.RoleRepository;
import com.ifelseelif.blsslab1.database.UserRepository;
import com.ifelseelif.blsslab1.models.domain.DbUser;
import com.ifelseelif.blsslab1.models.dto.UserDto;
import com.ifelseelif.blsslab1.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserDto userDto) {
        DbUser dbUser = new DbUser();

        dbUser.setUsername(userDto.getUsername());
        dbUser.setPassword(encodePassword(userDto.getPassword()));
        dbUser.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));

        userRepository.save(dbUser);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
