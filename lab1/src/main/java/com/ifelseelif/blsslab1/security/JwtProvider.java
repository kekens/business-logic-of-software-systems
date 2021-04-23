package com.ifelseelif.blsslab1.security;

import org.springframework.stereotype.Component;

public interface JwtProvider {
    boolean validateToken(String token);

    String getLoginFromToken(String token);

    String generateToken(String username);
}
