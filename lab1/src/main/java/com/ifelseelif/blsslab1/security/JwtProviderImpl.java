package com.ifelseelif.blsslab1.security;


import java.security.SecureRandom;
import java.util.*;

public class JwtProviderImpl implements JwtProvider {
    private Set<String> tokenCache = new HashSet<>();

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    @Override
    public boolean validateToken(String token) {
        return tokenCache.contains(token);
    }

    @Override
    public String generateToken() {
        String token = generateNewToken();
        tokenCache.add(token);

        return token;
    }

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
