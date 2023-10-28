package com.afarid.departementservice.service.impl;

import com.afarid.departementservice.dto.DepartmentDto;
import com.afarid.departementservice.dto.EmployeeDto;
import com.afarid.departementservice.entity.Department;
import com.afarid.departementservice.exception.ResourceNotFoundException;
import com.afarid.departementservice.feign.DepartmentClient;
import com.afarid.departementservice.mapper.DepartmentMapper;
import com.afarid.departementservice.repository.DepartmentRepository;
import com.afarid.departementservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final DepartmentClient departmentClient;
    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Department with code : %s not found!", code))
        );

//        departmentClient.getAllEmployeesByDepartmentCode(department.getDepartmentCode());

        //Using ModelMapper
        return departmentMapper.toDepartmentDto(department);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        //Using ModelMapper
        Department department = departmentMapper.toDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        //Using ModelMapper
        return departmentMapper.toDepartmentDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> {
                    List<EmployeeDto> employeesDto = departmentClient.getAllEmployeesByDepartmentCode(department.getDepartmentCode());
                    setDepartmentCodeForEmployees(employeesDto, department.getDepartmentCode());
                    return new DepartmentDto(
                            department.getId(),
                            department.getDepartmentName(),
                            department.getDepartmentDescription(),
                            department.getDepartmentCode(),
                            employeesDto
                    );
                })
                .toList();
    }

    private void setDepartmentCodeForEmployees(List<EmployeeDto> employeesDto, String departmentCode) {
        employeesDto.forEach(employeeDto -> employeeDto.setDepartmentCode(departmentCode));
    }

}
