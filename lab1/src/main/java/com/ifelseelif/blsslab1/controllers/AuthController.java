package com.ifelseelif.blsslab1.controllers;

import com.ifelseelif.blsslab1.models.dto.UserDto;
import com.ifelseelif.blsslab1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserDto userDto) {
        this.authService.register(userDto);
    }

    @PostMapping("/login")
    public void login() {
        throw new NotImplementedException();
    }



}
