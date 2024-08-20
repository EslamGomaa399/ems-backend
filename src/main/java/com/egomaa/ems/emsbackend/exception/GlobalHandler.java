package com.egomaa.ems.emsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExists exists) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(exists.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exists) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exists.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }




}
