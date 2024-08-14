package com.egomaa.ems.emsbackend.controller;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.dto.JobDTO;
import com.egomaa.ems.emsbackend.entity.Employee;
import com.egomaa.ems.emsbackend.service.EmployeeService;
import com.egomaa.ems.emsbackend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("jobs")
    public ResponseEntity<?> getAllJobs(){
        List<JobDTO> allJobs = jobService.getAllJobs();
        return new ResponseEntity<>(allJobs,HttpStatus.OK);
    }



}
