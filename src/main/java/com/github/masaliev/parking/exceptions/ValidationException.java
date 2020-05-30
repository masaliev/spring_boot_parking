package com.github.masaliev.parking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Getter
public class ValidationException extends RuntimeException {
    private final BindingResult bindingResult;
}
