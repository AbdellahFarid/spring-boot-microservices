package com.afarid.departementservice.feign;

import com.afarid.departementservice.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8081", name = "EMPLOYEE-SERVICE")
public interface DepartmentClient {

    @GetMapping("/api/v1/employees/department/{department-code}")
    List<EmployeeDto> getAllEmployeesByDepartmentCode(@PathVariable("department-code") String departmentCode);
}
