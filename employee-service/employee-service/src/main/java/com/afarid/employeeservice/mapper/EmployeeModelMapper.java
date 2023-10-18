package com.afarid.employeeservice.mapper;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeModelMapper {

    private final ModelMapper modelMapper;


    public EmployeeDto mapToEmployeeDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee mapToEmployee(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }
}
