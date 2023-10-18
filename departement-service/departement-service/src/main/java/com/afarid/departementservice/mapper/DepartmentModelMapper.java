package com.afarid.departementservice.mapper;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.entity.Department;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepartmentModelMapper {

    private final ModelMapper modelMapper;

    public DepartmentDto mapToDepartmentDto(Department department){
        return modelMapper.map(department, DepartmentDto.class);
    }

    public Department mapToDepartment(DepartmentDto departmentDto){
        return modelMapper.map(departmentDto, Department.class);
    }
}
