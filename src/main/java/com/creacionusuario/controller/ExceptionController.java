package com.creacionusuario.controller;

import com.creacionusuario.constants.Constants;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailExisteException;
import com.creacionusuario.exception.EmailValidationExcepcion;
import com.creacionusuario.models.ResponseModel;
import com.creacionusuario.utils.ErrorResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseModel> handleGenericException(Exception ex){
        return ErrorResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseModel> noContentException(Exception ex){
        return ErrorResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, Constants.NO_RESULT);
    }
    @ExceptionHandler(EmailValidationExcepcion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleValidationException(EmailValidationExcepcion ex) {
        return ErrorResponseUtils.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ExceptionHandler(EmailExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseModel> conflictException(EmailExisteException ex) {
        return ErrorResponseUtils.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
