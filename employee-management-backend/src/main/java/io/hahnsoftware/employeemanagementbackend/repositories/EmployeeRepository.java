package io.hahnsoftware.employeemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.hahnsoftware.employeemanagementbackend.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
}
