package com.ifelseelif.blsslab1.controllers;

import com.ifelseelif.blsslab1.models.domain.DbUser;
import com.ifelseelif.blsslab1.models.dto.AuthRequest;
import com.ifelseelif.blsslab1.models.dto.AuthResponse;
import com.ifelseelif.blsslab1.models.dto.RegistrationRequest;
import com.ifelseelif.blsslab1.security.JwtProvider;
import com.ifelseelif.blsslab1.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(IUserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        DbUser u = new DbUser();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getLogin());
        userService.saveUser(u);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse auth(@RequestBody AuthRequest request) {
        DbUser userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }
}