package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.models.dto.UserDto;
import com.ifelseelif.blsslab1.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {

    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
