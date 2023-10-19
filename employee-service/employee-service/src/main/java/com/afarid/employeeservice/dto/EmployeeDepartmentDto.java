package com.afarid.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDepartmentDto {

    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}
