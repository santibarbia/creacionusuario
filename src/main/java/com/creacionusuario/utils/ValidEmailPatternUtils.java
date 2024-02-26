package com.creacionusuario.utils;

import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailValidationExcepcion;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ValidEmailPatternUtils {

    private static final String EMAIL_PATTERN = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
    public static void emailPatterValid(String email) throws ApiException {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new EmailValidationExcepcion("Formato de email no valido");
        };
    }
}
