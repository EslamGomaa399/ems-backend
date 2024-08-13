package com.egomaa.ems.emsbackend.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private int code;
    private String message;

}
