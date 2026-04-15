package com.example.MCPPOC_1.tools;
import com.example.MCPPOC_1.dto.EmployeeRequest;
import com.example.MCPPOC_1.model.Employee;
import com.example.MCPPOC_1.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class EmployeeTools {

    @Bean
    @Description("""
Create employee strictly using primitive values only.
DO NOT use nested objects.

Rules:
- empId: string
- empName: string
- empSalary: number
- age: number
- gender: string
- address: string only (example: "Hyderabad")
  """)
    public Function<EmployeeRequest, Employee> createEmployee(EmployeeService service) {
        return service::createEmployee;
    }


    @Bean
    @Description("Get all employees")
    public Supplier<List<Employee>> getAllEmployees(EmployeeService service) {
        return service::getAllEmployees;
    }

    @Bean
    @Description("Get employee by empId")
    public Function<String, Employee> getEmployee(EmployeeService service) {
        return service::getEmployeeByEmpId;
    }

    @Bean
    @Description("Delete employee by empId")
    public Function<String, String> deleteEmployee(EmployeeService service) {
        return empId -> {
            service.deleteEmployee(empId);
            return "Deleted: " + empId;
        };
    }

    @Bean
    @Description("Update employee by empId using full Employee object")
    public Function<EmployeeRequest, Employee> updateEmployee(EmployeeService service) {
        return emp -> {
            Employee updated = new Employee();
            updated.setEmpName(emp.getEmpName());
            updated.setEmpSalary(emp.getEmpSalary());
            updated.setAge(emp.getAge());
            updated.setGender(emp.getGender());
            updated.setAddress(emp.getAddress());

            return service.updateEmployee(emp.getEmpId(), updated);
        };
    }
}