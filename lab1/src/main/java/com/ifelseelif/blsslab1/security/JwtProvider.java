package com.ifelseelif.blsslab1.security;

public interface JwtProvider {
    boolean validateToken(String token);

    String generateToken(String login);
}
