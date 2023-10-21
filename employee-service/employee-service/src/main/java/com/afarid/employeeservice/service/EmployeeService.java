package com.afarid.employeeservice.service;

import com.afarid.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    List<EmployeeDto> getEmployeesByDepartmentCode(String departmentCode);
}
