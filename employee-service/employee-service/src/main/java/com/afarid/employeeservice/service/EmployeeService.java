package com.afarid.employeeservice.service;

import com.afarid.employeeservice.dto.EmployeeDepartmentDto;
import com.afarid.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDepartmentDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();
}
