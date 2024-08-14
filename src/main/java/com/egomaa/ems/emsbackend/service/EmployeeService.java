package com.egomaa.ems.emsbackend.service;

import com.egomaa.ems.emsbackend.dto.EmployeeDTO;
import com.egomaa.ems.emsbackend.entity.Address;
import com.egomaa.ems.emsbackend.entity.Department;
import com.egomaa.ems.emsbackend.entity.Employee;
import com.egomaa.ems.emsbackend.exception.EmailAlreadyExists;
import com.egomaa.ems.emsbackend.exception.NotFoundException;
import com.egomaa.ems.emsbackend.repository.AddressRepository;
import com.egomaa.ems.emsbackend.repository.DepartmentRepository;
import com.egomaa.ems.emsbackend.repository.EmployeeRepository;
import com.egomaa.ems.emsbackend.repository.JobRepository;
import com.egomaa.ems.emsbackend.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    private final ModelMapper mapper;

    @Transactional
    public Employee saveEmployee(EmployeeDTO employeeDTO) throws ParseException {

        Employee employee = mapper.map(employeeDTO,Employee.class);

        // Convert hireDate to the desired format
        if (employeeDTO.getHireDate() != null) {
            String convertedDate = DateUtil.convertToSimpleDateFormat(employeeDTO.getHireDate());
            employee.setHireDate(convertedDate);
        }

        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new EmailAlreadyExists("This email already exists");
        }

        employee.setJob(jobRepository.findByJobTitle(employeeDTO.getJobTitle()));
        Department dept = departmentRepository.findByDepartmentName(employeeDTO.getDepartmentName());
        employee.setDepartment(dept);
        String managerName = employeeDTO.getManagerName();

        if (employeeDTO.getManagerName() != null) {
            String[] managerNames = employeeDTO.getManagerName().split(" ");
            Employee manager = employeeRepository.findByFirstNameAndLastName(managerNames[0], managerNames[1]);

            if (manager == null) {
                throw new NotFoundException("Manager not found: " + employeeDTO.getManagerName());
            }

            employee.setManager(manager);
        }

        Address address = new Address();
        address.setCity(employeeDTO.getCity());
        address.setStreet(employeeDTO.getStreet());
        address.setCountry(employeeDTO.getCountry());
        address.setPostalCode(employeeDTO.getPostalCode());
        addressRepository.save(address);

        employee.setAddress(address);

        Employee savedEmp = employeeRepository.save(employee);

        return savedEmp;
    }




}
