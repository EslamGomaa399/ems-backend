package com.egomaa.ems.emsbackend.service;

import com.egomaa.ems.emsbackend.dto.JobDTO;
import com.egomaa.ems.emsbackend.entity.Job;
import com.egomaa.ems.emsbackend.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ModelMapper mapper;

    public List<JobDTO> getAllJobs(){

        List<JobDTO> jobsDTOS = jobRepository.findAll()
                .stream()
                .map(job -> mapper.map(job, JobDTO.class))
                .collect(Collectors.toList());

        return jobsDTOS;
    }



}
