package io.hahnsoftware.employeemanagementbackend.controllers;

import io.hahnsoftware.employeemanagementbackend.dtos.DepartmentDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.InvalidInputException;
import io.hahnsoftware.employeemanagementbackend.services.DepartmentService;
import io.hahnsoftware.employeemanagementbackend.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDto>> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) throws InvalidInputException {
        DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Department created successfully", createdDepartment));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDto>> getDepartmentById(@PathVariable Long id) throws DepartmentNotFoundException {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>("Department fetched successfully", departmentDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDto>> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) throws DepartmentNotFoundException {
        DepartmentDto updatedDepartment = departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok(new ApiResponse<>("Department updated successfully", updatedDepartment));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) throws DepartmentNotFoundException {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentDto>>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(new ApiResponse<>("Departments fetched successfully", departments));
    }
}
