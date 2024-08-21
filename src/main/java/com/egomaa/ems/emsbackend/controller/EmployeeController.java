package com.egomaa.ems.emsbackend.controller;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.entity.Employee;
import com.egomaa.ems.emsbackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService ;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees,HttpStatus.OK);
    }

    @GetMapping("/employees/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }

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

    @GetMapping("managers/{employeeId}")
    public ResponseEntity<?> getManagerByEmployeeId(@PathVariable Long employeeId) {
        Employee manager = employeeService.getManagerByEmployeeId(employeeId);
        return new ResponseEntity<>(manager, HttpStatus.CREATED);
    }


}
