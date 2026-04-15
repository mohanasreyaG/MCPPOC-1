package com.example.MCPPOC_1.service;

import com.example.MCPPOC_1.model.Employee;
import com.example.MCPPOC_1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    public Employee createEmployee(Employee employee) {
        if (repository.existsByEmpId(employee.getEmpId())) {
            throw new RuntimeException("Employee already exists with empId: " + employee.getEmpId());
        }
        return repository.save(employee);
    }


    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }


    public Employee getEmployeeByEmpId(String empId) {
        return repository.findByEmpId(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with empId: " + empId));
    }


    public Employee updateEmployee(String empId, Employee updatedEmployee) {

        Employee existing = getEmployeeByEmpId(empId);

        existing.setEmpName(updatedEmployee.getEmpName());
        existing.setEmpSalary(updatedEmployee.getEmpSalary());
        existing.setAge(updatedEmployee.getAge());
        existing.setGender(updatedEmployee.getGender());
        existing.setAddress(updatedEmployee.getAddress());

        return repository.save(existing);
    }


    public void deleteEmployee(String empId) {
        if (!repository.existsByEmpId(empId)) {
            throw new RuntimeException("Employee not found with empId: " + empId);
        }
        repository.deleteByEmpId(empId);
    }
}