package com.afarid.employeeservice.service.impl;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import com.afarid.employeeservice.repository.EmployeeRepository;
import com.afarid.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //Using constructor
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        //Save the employee in the database
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        //Using the constructor
        Employee employee = employeeRepository.findById(id).get();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}
