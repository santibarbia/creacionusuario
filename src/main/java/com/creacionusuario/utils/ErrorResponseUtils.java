package com.creacionusuario.utils;

import com.creacionusuario.models.ResponseModel;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ErrorResponseUtils {

    public static ResponseEntity<ResponseModel> buildErrorResponse(HttpStatus status, String message){
        ResponseModel responseModel = ResponseModel.builder()
                .codigo(status.value())
                .mensaje(message).build();
        return new ResponseEntity<>(responseModel, status);
    }
}
