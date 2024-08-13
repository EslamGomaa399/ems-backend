package com.egomaa.ems.emsbackend.repository;

import com.egomaa.ems.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByEmail(String email);

}
