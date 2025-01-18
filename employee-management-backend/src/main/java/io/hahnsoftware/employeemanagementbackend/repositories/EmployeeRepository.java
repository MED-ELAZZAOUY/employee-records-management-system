package io.hahnsoftware.employeemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.hahnsoftware.employeemanagementbackend.entities.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
