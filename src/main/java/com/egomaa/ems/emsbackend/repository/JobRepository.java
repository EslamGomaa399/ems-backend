package com.egomaa.ems.emsbackend.repository;

import com.egomaa.ems.emsbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

    Job findByJobTitle(String jobTitle);

}
