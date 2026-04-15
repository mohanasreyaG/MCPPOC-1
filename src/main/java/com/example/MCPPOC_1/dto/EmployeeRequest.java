
package com.example.MCPPOC_1.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String empId;
    private String empName;
    private Double empSalary;
    private Integer age;
    private String gender;
    private String address;
}