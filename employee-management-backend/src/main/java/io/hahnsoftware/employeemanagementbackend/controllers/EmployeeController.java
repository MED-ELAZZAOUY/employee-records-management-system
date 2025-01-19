package io.hahnsoftware.employeemanagementbackend.controllers;


import io.hahnsoftware.employeemanagementbackend.dtos.EmployeeDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.EmployeeNotFoundException;
import io.hahnsoftware.employeemanagementbackend.services.EmployeeService;
import io.hahnsoftware.employeemanagementbackend.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) throws DepartmentNotFoundException {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Employee created successfully", createdEmployee));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(new ApiResponse<>("Employee fetched successfully", employeeDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) throws EmployeeNotFoundException, DepartmentNotFoundException {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(new ApiResponse<>("Employee updated successfully", updatedEmployee));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDto>>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EmployeeDto> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Employees fetched successfully", employees));
    }
}
