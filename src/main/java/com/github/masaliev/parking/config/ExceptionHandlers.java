package com.github.masaliev.parking.config;

import com.github.masaliev.parking.exceptions.InvalidPlaceIdException;
import com.github.masaliev.parking.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = {InvalidPlaceIdException.class})
    public ResponseEntity invalidPlaceIdException(InvalidPlaceIdException e, WebRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity validationException(ValidationException e, WebRequest request) {

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errors = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> {
                    String message = fieldError.getDefaultMessage();
                    return message != null ? message : "error";
                }));
        return ResponseEntity.badRequest().body(errors);
    }
}
