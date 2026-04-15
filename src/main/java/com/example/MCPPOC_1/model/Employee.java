package com.example.MCPPOC_1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
public class Employee {
    @Id
    private String id;

    private String empId;
    private String empName;
    private Double empSalary;
    private Integer age;
    private String gender;
    private String address;


}
