package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.RoleRepository;
import com.ifelseelif.blsslab1.database.UserRepository;
import com.ifelseelif.blsslab1.models.domain.DbUser;
import com.ifelseelif.blsslab1.models.dto.AuthResponse;
import com.ifelseelif.blsslab1.models.dto.UserDto;
import com.ifelseelif.blsslab1.security.JwtProvider;
import com.ifelseelif.blsslab1.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
        DbUser dbUser = new DbUser();

        dbUser.setUsername(userDto.getUsername());
        dbUser.setPassword(encodePassword(userDto.getPassword()));
        dbUser.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));

        userRepository.save(dbUser);
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
