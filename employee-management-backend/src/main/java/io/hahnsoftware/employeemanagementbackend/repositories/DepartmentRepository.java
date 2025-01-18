package io.hahnsoftware.employeemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Object> findByName(String departmentName);
}
