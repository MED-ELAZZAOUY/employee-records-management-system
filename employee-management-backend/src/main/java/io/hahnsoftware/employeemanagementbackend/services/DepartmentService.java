package io.hahnsoftware.employeemanagementbackend.services;

import io.hahnsoftware.employeemanagementbackend.dtos.DepartmentDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.InvalidInputException;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto) throws InvalidInputException;

    DepartmentDto getDepartmentById(Long id) throws DepartmentNotFoundException;

    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) throws DepartmentNotFoundException;

    void deleteDepartment(Long id) throws DepartmentNotFoundException;

    List<DepartmentDto> getAllDepartments();
}
