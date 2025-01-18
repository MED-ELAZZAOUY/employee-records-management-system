package io.hahnsoftware.employeemanagementbackend.services;

import io.hahnsoftware.employeemanagementbackend.dtos.EmployeeDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto) throws DepartmentNotFoundException;

    EmployeeDto getEmployeeById(Long id) throws EmployeeNotFoundException;

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) throws EmployeeNotFoundException, DepartmentNotFoundException;

    void deleteEmployee(Long id) throws EmployeeNotFoundException;

    List<EmployeeDto> getAllEmployees();
}
