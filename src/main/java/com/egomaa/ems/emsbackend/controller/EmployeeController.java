package com.egomaa.ems.emsbackend.controller;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.entity.Employee;
import com.egomaa.ems.emsbackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService ;

    @PostMapping("employees")
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) throws ParseException {
        Employee employee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }



}
