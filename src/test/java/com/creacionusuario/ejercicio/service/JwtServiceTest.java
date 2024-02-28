package com.creacionusuario.ejercicio.service;

import com.creacionusuario.config.JwtConfig;
import com.creacionusuario.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class JwtServiceTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldGenerateTokenSuccessfull(){
        String username = "userTest";
        when(jwtConfig.generateToken(username)).thenReturn("aMockToken");

        String token = jwtService.generateToken(username);
        assertEquals("aMockToken", token);
        verify(jwtConfig, times(1)).generateToken(username);
    }

    @Test
    void shouldValidateTokenSuccessfull(){
        String username = "userTest";
        String token = "aMockToken";

        when(jwtConfig.validateToken(token,username)).thenReturn(true);

        Boolean isValid = jwtService.validateToken(token,username);

        assertTrue(isValid);
        verify(jwtConfig, times(1)).validateToken(token, username);
    }
}
