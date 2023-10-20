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
    public EmployeeDepartmentDto getEmployeeById(Long id) {

/*        //Using the constructor
        Employee employee = employeeRepository.findById(id).get();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );*/

/*        //Using MapStruct
        Employee employee = employeeRepository.findById(id).get();
        return EmployeeMapper.EMPLOYEE_MAPPER.toEmployeeDto(employee);*/

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Employee with id: %s not found!", id))
        );
        DepartmentDto departmentDto = employeeClient.getDepartmentByCode(employee.getDepartmentCode());

        EmployeeDepartmentDto employeeDepartmentDto = new EmployeeDepartmentDto();
        employeeDepartmentDto.setEmployeeDto(employeeModelMapper.mapToEmployeeDto(employee));
        employeeDepartmentDto.setDepartmentDto(departmentDto);

        return employeeDepartmentDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(employeeModelMapper::mapToEmployeeDto).collect(Collectors.toList());
    }
}
