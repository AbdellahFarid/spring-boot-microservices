package com.afarid.employeeservice.feign;

import com.afarid.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://localhost:8080")
public interface FeignEmployeeClient {
    @GetMapping("/api/v1/departments/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
}
