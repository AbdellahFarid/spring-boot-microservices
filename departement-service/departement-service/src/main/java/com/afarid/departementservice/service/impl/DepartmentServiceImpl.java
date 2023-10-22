package com.afarid.departementservice.service.impl;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.dto.EmployeeDto;
import com.afarid.departementservice.entity.Department;
import com.afarid.departementservice.exception.ResourceNotFoundException;
import com.afarid.departementservice.feign.DepartmentClient;
import com.afarid.departementservice.mapper.DepartmentModelMapper;
import com.afarid.departementservice.repository.DepartmentRepository;
import com.afarid.departementservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentModelMapper departmentModelMapper;
    private final DepartmentClient departmentClient;
    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Department with code : %s not found!", code))
        );

//        departmentClient.getAllEmployeesByDepartmentCode(department.getDepartmentCode());

        //Using ModelMapper
        return departmentModelMapper.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        //Using ModelMapper
        Department department = departmentModelMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        //Using ModelMapper
        return departmentModelMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentsDto = departments
                .stream()
                .map(department -> {
                    List<EmployeeDto> employeesDto = departmentClient.getAllEmployeesByDepartmentCode(department.getDepartmentCode());
                    employeesDto.forEach(employeeDto -> employeeDto.setDepartmentCode(department.getDepartmentCode()));
                    DepartmentDto departmentDto = new DepartmentDto(
                            department.getId(),
                            department.getDepartmentName(),
                            department.getDepartmentDescription(),
                            department.getDepartmentCode(),
                            employeesDto
                    );
                    return departmentDto;
                }).toList();
        return departmentsDto;
    }
}
