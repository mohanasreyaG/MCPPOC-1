package com.example.MCPPOC_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MCPPOC_1.model.Employee;
import com.example.MCPPOC_1.service.EmployeeService;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }


    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }


    @GetMapping("/{empId}")
    public Employee getByEmpId(@PathVariable String empId) {
        return service.getEmployeeByEmpId(empId);
    }


    @PutMapping("/{empId}")
    public Employee update(@PathVariable String empId,
                           @RequestBody Employee employee) {
        return service.updateEmployee(empId, employee);
    }


    @DeleteMapping("/{empId}")
    public String delete(@PathVariable String empId) {
        service.deleteEmployee(empId);
        return "Employee deleted successfully";
    }
}