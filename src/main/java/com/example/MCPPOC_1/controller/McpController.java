package com.example.MCPPOC_1.controller;

import com.example.MCPPOC_1.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mcp")
public class McpController {

    private final EmployeeService service;

    public McpController(EmployeeService service) {
        this.service = service;
    }

    // 🔥 TOOL LIST (MCP style)
    @GetMapping("/tools")
    public Map<String, Object> tools() {
        Map<String, Object> tools = new HashMap<>();

        tools.put("createEmployee", "Create employee with full details");
        tools.put("getAllEmployees", "Get all employees");
        tools.put("getEmployee", "Get employee by empId");
        tools.put("updateEmployee", "Update employee");
        tools.put("deleteEmployee", "Delete employee");

        return tools;
    }

    // 🔥 EXECUTE TOOL (MCP style)
    @PostMapping("/execute")
    public Object execute(@RequestBody Map<String, Object> request) {

        String tool = (String) request.get("tool");

        switch (tool) {

            case "getAllEmployees":
                return service.getAllEmployees();

            case "getEmployee":
                return service.getEmployeeByEmpId((String) request.get("empId"));

            case "deleteEmployee":
                service.deleteEmployee((String) request.get("empId"));
                return "Deleted";

            default:
                return "Tool not supported";
        }
    }
}
