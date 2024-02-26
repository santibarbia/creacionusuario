package com.creacionusuario.exception;

public class EmailExisteException extends ApiException{
    public EmailExisteException() {
    }

    public EmailExisteException(String message) {
        super(message);
    }
}
