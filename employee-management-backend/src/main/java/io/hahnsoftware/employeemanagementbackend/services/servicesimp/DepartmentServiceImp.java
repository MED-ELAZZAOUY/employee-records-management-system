package io.hahnsoftware.employeemanagementbackend.services.servicesimp;

import io.hahnsoftware.employeemanagementbackend.dtos.DepartmentDto;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import io.hahnsoftware.employeemanagementbackend.exceptions.DepartmentNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.InvalidInputException;
import io.hahnsoftware.employeemanagementbackend.mappers.DepartmentMapper;
import io.hahnsoftware.employeemanagementbackend.repositories.DepartmentRepository;
import io.hahnsoftware.employeemanagementbackend.services.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImp(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) throws InvalidInputException {
        if (departmentDto.getName() == null || departmentDto.getName().isBlank()) {
            throw new InvalidInputException("Department name cannot be empty");
        }
        Department department = DepartmentMapper.mapToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDto(savedDepartment);
    }


    @Override
    public DepartmentDto getDepartmentById(Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        return DepartmentMapper.mapToDto(department);
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) throws DepartmentNotFoundException {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        existingDepartment.setName(departmentDto.getName());
        return DepartmentMapper.mapToDto(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        departmentRepository.delete(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::mapToDto)
                .toList();
    }
}
