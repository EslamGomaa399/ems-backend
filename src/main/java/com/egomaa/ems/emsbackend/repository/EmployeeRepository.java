package com.egomaa.ems.emsbackend.repository;

import com.egomaa.ems.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByEmail(String email);


    @Query(value = "SELECT DISTINCT e.manager_id FROM employees e WHERE e.manager_id IS NOT NULL", nativeQuery = true)
    List<Integer> findAllManagerIds();

    @Query(value = "UPDATE employees SET is_deleted = 1 WHERE employee_id = :id;", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteEmployee(Long id);




}
