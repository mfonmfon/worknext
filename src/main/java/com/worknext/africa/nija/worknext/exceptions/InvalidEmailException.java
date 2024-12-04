package com.worknext.africa.nija.worknext.exceptions;

public class InvalidEmailException extends IllegalArgumentException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
