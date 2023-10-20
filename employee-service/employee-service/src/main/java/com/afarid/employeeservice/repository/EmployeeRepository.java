package com.afarid.employeeservice.repository;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<List<Employee>> findEmployeeByDepartmentCode(String departmentCode);
}
