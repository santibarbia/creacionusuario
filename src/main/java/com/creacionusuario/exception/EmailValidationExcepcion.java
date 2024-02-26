package com.creacionusuario.exception;

public class EmailValidationExcepcion extends ApiException{

    public EmailValidationExcepcion() {
    }

    public EmailValidationExcepcion(String message) {
        super(message);
    }
}
