package io.hahnsoftware.employeemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.hahnsoftware.employeemanagementbackend.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
