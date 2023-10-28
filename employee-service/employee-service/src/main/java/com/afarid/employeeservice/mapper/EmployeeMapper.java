package com.afarid.employeeservice.mapper;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);
}
