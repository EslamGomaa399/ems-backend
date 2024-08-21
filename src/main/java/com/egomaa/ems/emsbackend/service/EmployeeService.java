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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<String> getAllManagersNames() {
        List<Integer> managerIds = employeeRepository.findAllManagerIds();

        return managerIds.stream().map(managerId -> {
            Employee employeeById = employeeRepository.findById(Long.valueOf(managerId)).get();
            String fullName = employeeById.getFirstName() + " " + employeeById.getLastName();
            return fullName;
        }).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> {
                    if (employee.getIsDeleted() == 0){
                        EmployeeDTO employeeDTO = mapToEmployeeDTO(employee);
                        return employeeDTO;
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteEmployee(id);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id : " + id));
        return mapToEmployeeDTO(employee);
    }


    public Employee getManagerByEmployeeId(Long employeeId) {
        Employee manager = employeeRepository.findById(employeeId)
                .orElseThrow(null);
        return manager;
    }

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setJobTitle(employee.getJob().getJobTitle());
        employeeDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setSalary(employee.getSalary());

        Employee manager = getManagerByEmployeeId(employee.getEmployeeId());
        String managerFullName = manager.getFirstName() + " " + manager.getLastName();
        employeeDTO.setManagerName(managerFullName);

        employeeDTO.setCountry(employee.getAddress().getCountry());
        employeeDTO.setCity(employee.getAddress().getCity());
        employeeDTO.setStreet(employee.getAddress().getStreet());
        employeeDTO.setPostalCode(employee.getAddress().getPostalCode());

        return employeeDTO;
    }




}
