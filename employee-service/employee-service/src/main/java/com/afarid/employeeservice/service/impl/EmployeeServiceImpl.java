package com.afarid.employeeservice.service.impl;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import com.afarid.employeeservice.mapper.EmployeeMapper;
import com.afarid.employeeservice.mapper.EmployeeModelMapper;
import com.afarid.employeeservice.repository.EmployeeRepository;
import com.afarid.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelMapper employeeModelMapper;
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

        Employee employee = employeeRepository.findById(id).get();

        return employeeModelMapper.mapToEmployeeDto(employee);
    }
}
