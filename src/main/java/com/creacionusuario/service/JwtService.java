package com.creacionusuario.service;

import com.creacionusuario.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;

    public String generateToken(String username){
        return jwtConfig.generateToken(username);
    }

    public Boolean validateToken(String token, String username){
        return jwtConfig.validateToken(token, username);
    }
}
