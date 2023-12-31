package com.afarid.employeeservice.service.impl;

import com.afarid.employeeservice.dto.DepartmentDto;
import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import com.afarid.employeeservice.exception.ResourceNotFoundException;
import com.afarid.employeeservice.feign.EmployeeClient;
import com.afarid.employeeservice.mapper.EmployeeMapper;
import com.afarid.employeeservice.repository.EmployeeRepository;
import com.afarid.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //Using ModelMapper
        Employee employee = employeeMapper.toEmployee(employeeDto);
        //Save the employee in the database
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Employee with id: %s not found!", id))
        );
        DepartmentDto departmentDto = employeeClient.getDepartmentByCode(employee.getDepartmentCode());

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                departmentDto
        );
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        System.out.println(employees);
        List<EmployeeDto> employeesDto = employees
                .stream()
                        .map(employee -> {
                            DepartmentDto departmentDto = employeeClient.getDepartmentByCode(employee.getDepartmentCode());
                            System.out.println(departmentDto);
                            EmployeeDto employeeDto = new EmployeeDto(
                                    employee.getId(),
                                    employee.getFirstName(),
                                    employee.getLastName(),
                                    employee.getEmail(),
                                    departmentDto
                            );
                            return employeeDto;
                        }).toList();
        System.out.println(employeesDto);
        return employeesDto;
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartmentCode(String departmentCode) {
        List<Employee> employees = employeeRepository.findEmployeeByDepartmentCode(departmentCode).get();
        List<EmployeeDto> employeesDto = employees
                .stream()
                .map(employee -> {
                    DepartmentDto departmentDto = employeeClient.getDepartmentByCode(employee.getDepartmentCode());
                    EmployeeDto employeeDto = new EmployeeDto(
                            employee.getId(),
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getEmail(),
                            departmentDto
                    );
                    return employeeDto;
                }).toList();
        return employeesDto;
    }
}
