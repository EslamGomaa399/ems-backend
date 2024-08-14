package com.egomaa.ems.emsbackend.controller;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.entity.Employee;
import com.egomaa.ems.emsbackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    @GetMapping("managers/names")
    public ResponseEntity<?> getAllManagersNames() {
        List<String> allManagersNames = employeeService.getAllManagersNames();
        return new ResponseEntity<>(allManagersNames, HttpStatus.CREATED);
    }



}
