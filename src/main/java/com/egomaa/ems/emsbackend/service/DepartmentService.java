package com.egomaa.ems.emsbackend.service;

import com.egomaa.ems.emsbackend.dto.DepartmentDTO;
import com.egomaa.ems.emsbackend.entity.Department;
import com.egomaa.ems.emsbackend.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(department -> mapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
        return departmentDTOS;
    }
}
