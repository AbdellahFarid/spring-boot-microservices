package com.afarid.departementservice.mapper;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper DEPARTMENT_MAPPER = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto toDepartmentDto(Department department);

    Department toDepartment(DepartmentDto departmentDto);
}
