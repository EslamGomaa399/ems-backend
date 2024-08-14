package com.egomaa.ems.emsbackend.controller;

import com.egomaa.ems.emsbackend.dto.DepartmentDTO;
import com.egomaa.ems.emsbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("departments")
    public ResponseEntity<?> getAllDepartments(){
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDTOList, HttpStatus.OK);
    }





}
