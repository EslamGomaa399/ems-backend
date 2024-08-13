package com.egomaa.ems.emsbackend.service;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.entity.Employee;

import java.text.ParseException;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDTO employeeDTO) throws ParseException;
}
