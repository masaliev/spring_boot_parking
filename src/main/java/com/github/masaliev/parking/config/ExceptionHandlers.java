package com.github.masaliev.parking.config;

import com.github.masaliev.parking.exceptions.InvalidPlaceIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = {InvalidPlaceIdException.class})
    public ResponseEntity duplicateUsernameException(InvalidPlaceIdException e, WebRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
