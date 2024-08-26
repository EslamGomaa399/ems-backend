package com.egomaa.ems.emsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class EmsBackendApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(EmsBackendApplication.class, args);
        System.out.println("-------------------------------");
    }

}
