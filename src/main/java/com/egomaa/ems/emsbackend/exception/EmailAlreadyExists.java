package com.egomaa.ems.emsbackend.exception;

public class EmailAlreadyExists extends RuntimeException {

    public EmailAlreadyExists(String message) {
        super(message);
    }
}
