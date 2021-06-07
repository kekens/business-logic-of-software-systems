package com.ifelseelif.responseservice.controllers;

import com.ifelseelif.responseservice.models.dto.AuthResponse;
import com.ifelseelif.responseservice.models.dto.UserDto;
import com.ifelseelif.responseservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserDto userDto) {
        this.authService.register(userDto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserDto userDto) {
        return authService.login(userDto);
    }
}
