package com.egomaa.ems.emsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobTitle;
    private Double salary;
    private String managerName;
    private String departmentName;
    private String country;
    private String city;
    private String street;
    private String postalCode;
}
