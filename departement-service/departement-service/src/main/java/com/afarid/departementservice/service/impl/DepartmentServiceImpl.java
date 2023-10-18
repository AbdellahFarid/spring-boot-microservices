package com.afarid.departementservice.service.impl;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.entity.Department;
import com.afarid.departementservice.exception.ResourceNotFoundException;
import com.afarid.departementservice.mapper.DepartmentMapper;
import com.afarid.departementservice.mapper.DepartmentModelMapper;
import com.afarid.departementservice.repository.DepartmentRepository;
import com.afarid.departementservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentModelMapper departmentModelMapper;
    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Department with code : %s not found!", code))
        );


        //mapping using constructor
/*        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );*/
        /*//Using MapStruct
        return DepartmentMapper.DEPARTMENT_MAPPER.toDepartmentDto(department);*/

        //Using ModelMapper
        return departmentModelMapper.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
/*
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
*/
        /*//Using MapStruct
        Department department = DepartmentMapper.DEPARTMENT_MAPPER.toDepartment(departmentDto);*/

        //Using ModelMapper
        Department department = departmentModelMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

/*        return new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );*/

        /*//Using MapStruct
        return DepartmentMapper.DEPARTMENT_MAPPER.toDepartmentDto(savedDepartment);*/

        //Using ModelMapper
        return departmentModelMapper.mapToDepartmentDto(savedDepartment);
    }
}
