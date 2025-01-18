package io.hahnsoftware.employeemanagementbackend.services.servicesimp;

import io.hahnsoftware.employeemanagementbackend.dtos.EmployeeDto;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import io.hahnsoftware.employeemanagementbackend.entities.Employee;
import io.hahnsoftware.employeemanagementbackend.enums.EmploymentStatus;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.EmployeeNotFoundException;
import io.hahnsoftware.employeemanagementbackend.mappers.EmployeeMapper;
import io.hahnsoftware.employeemanagementbackend.repositories.DepartmentRepository;
import io.hahnsoftware.employeemanagementbackend.repositories.EmployeeRepository;
import io.hahnsoftware.employeemanagementbackend.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws DepartmentNotFoundException {
        Department department = (Department) departmentRepository.findByName(employeeDto.getDepartmentName())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        Employee employee = EmployeeMapper.mapToEntity(employeeDto, department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return EmployeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) throws EmployeeNotFoundException, DepartmentNotFoundException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Department department = (Department) departmentRepository.findByName(employeeDto.getDepartmentName())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        existingEmployee.setEmployeeId(employeeDto.getEmployeeId());
        existingEmployee.setFullName(employeeDto.getFullName());
        existingEmployee.setJobTitle(employeeDto.getJobTitle());
        existingEmployee.setDepartment(department);
        existingEmployee.setHireDate(employeeDto.getHireDate());
        existingEmployee.setEmploymentStatus(EmploymentStatus.valueOf(employeeDto.getEmploymentStatus()));
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setPhoneNumber(employeeDto.getPhoneNumber());
        existingEmployee.setAddress(EmployeeMapper.parseAddress(employeeDto.getAddress()));

        return EmployeeMapper.toDto(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }
}
