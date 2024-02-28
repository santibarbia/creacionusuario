package com.creacionusuario.ejercicio.controller;

import com.creacionusuario.controller.ExceptionController;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailExisteException;
import com.creacionusuario.exception.EmailValidationExcepcion;
import com.creacionusuario.models.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;
@Slf4j
public class ExceptionControllerTest {
    @InjectMocks
    private ExceptionController exceptionController;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }
    @Test
    void shouldReturnInternalServerError() {
        Exception ex = mock(Exception.class);
        ResponseEntity<ResponseModel> response = exceptionController.handleGenericException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    void shouldReturnNotFound() {
        ApiException ex = mock(ApiException.class);
        ResponseEntity<ResponseModel> response = exceptionController.noContentException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestEmailValidacion() {
        EmailValidationExcepcion ex = mock(EmailValidationExcepcion.class);
        ResponseEntity<ResponseModel> response = exceptionController.handleValidationException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestEmailExiste() {
        EmailExisteException ex = mock(EmailExisteException.class);
        ResponseEntity<ResponseModel> response = exceptionController.conflictException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
