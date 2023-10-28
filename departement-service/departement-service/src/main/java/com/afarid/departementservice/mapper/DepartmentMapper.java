package com.afarid.departementservice.mapper;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDepartmentDto(Department department);

    Department toDepartment(DepartmentDto departmentDto);
}
