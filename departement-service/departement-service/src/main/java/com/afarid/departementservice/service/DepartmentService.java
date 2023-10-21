package com.afarid.departementservice.service;

import com.afarid.departementservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto getDepartmentByCode(String code);

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();
}
