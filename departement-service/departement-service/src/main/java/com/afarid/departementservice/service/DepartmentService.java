package com.afarid.departementservice.service;

import com.afarid.departementservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto getDepartmentByCode(String code);

    DepartmentDto createDepartment(DepartmentDto departmentDto);
}
