package com.afarid.employeeservice.controller;

import com.afarid.employeeservice.dto.EmployeeDto;
import com.afarid.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RefreshScope
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        System.out.println(employeeDto);

        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/department/{department-code}")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByDepartmentCode(@PathVariable("department-code") String departmentCode){
        return new ResponseEntity<>(employeeService.getEmployeesByDepartmentCode(departmentCode), HttpStatus.OK);
    }
}
