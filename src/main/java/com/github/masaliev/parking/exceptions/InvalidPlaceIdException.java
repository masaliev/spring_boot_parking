package com.github.masaliev.parking.exceptions;

public class InvalidPlaceIdException extends RuntimeException {
    public InvalidPlaceIdException() {
        super("Invalid place id");
    }
}
