package com.ifelseelif.blsslab1.controllers;

import com.ifelseelif.blsslab1.models.dto.AuthResponse;
import com.ifelseelif.blsslab1.models.dto.UserDto;
import com.ifelseelif.blsslab1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
