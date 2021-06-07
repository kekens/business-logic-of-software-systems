package com.ifelseelif.responseservice.service;

import com.ifelseelif.responseservice.database.RoleRepository;
import com.ifelseelif.responseservice.database.UserRepository;
import com.ifelseelif.responseservice.models.domain.User;
import com.ifelseelif.responseservice.models.dto.AuthResponse;
import com.ifelseelif.responseservice.models.dto.UserDto;
import com.ifelseelif.responseservice.security.JwtProvider;
import com.ifelseelif.responseservice.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                       UserRepository userRepository, AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider)
    {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        Authentication authenticate = null;

        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),
                    userDto.getPassword()));
        } catch (AuthenticationException authenticationException) {
            return AuthResponse.builder()
                    .token("")
                    .build();
        }
        assert authenticate != null;
        String authenticationToken = jwtProvider.generateToken(authenticate.getName());
        System.out.println(authenticationToken);
        System.out.println(authenticate.getName());
        return AuthResponse.builder()
                .token(authenticationToken)
                .build();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
