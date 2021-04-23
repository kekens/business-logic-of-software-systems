package com.ifelseelif.blsslab1.models.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
