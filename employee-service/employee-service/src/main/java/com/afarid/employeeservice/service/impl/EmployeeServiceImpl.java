package com.afarid.employeeservice.service.impl;

import com.afarid.employeeservice.dto.DepartmentDto;
import com.afarid.employeeservice.dto.EmployeeDepartmentDto;
import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import com.afarid.employeeservice.exception.ResourceNotFoundException;
import com.afarid.employeeservice.feign.FeignEmployeeClient;
import com.afarid.employeeservice.mapper.EmployeeModelMapper;
import com.afarid.employeeservice.repository.EmployeeRepository;
import com.afarid.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelMapper employeeModelMapper;
    private final FeignEmployeeClient employeeClient;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //Using constructor
/*        Employee employee = new Employee(
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
        );*/
        /*//Using MapStruct
        Employee employee = EmployeeMapper.EMPLOYEE_MAPPER.toEmployee(employeeDto);
        //Save the employee in the database
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.EMPLOYEE_MAPPER.toEmployeeDto(savedEmployee);*/

        //Using ModelMapper
        Employee employee = employeeModelMapper.mapToEmployee(employeeDto);
        //Save the employee in the database
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeModelMapper.mapToEmployeeDto(savedEmployee);
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
                }).collect(Collectors.toList());
        return employeesDto;
    }
}
