package com.example.MCPPOC_1.repository;

import com.example.MCPPOC_1.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface  EmployeeRepository extends MongoRepository<Employee,String>{
    Optional<Employee> findByEmpId(String empId);
    boolean existsByEmpId(String empId);
    void deleteByEmpId(String empId);
}
