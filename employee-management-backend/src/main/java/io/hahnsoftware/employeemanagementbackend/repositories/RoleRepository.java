package io.hahnsoftware.employeemanagementbackend.repositories;

import io.hahnsoftware.employeemanagementbackend.entities.Role;
import io.hahnsoftware.employeemanagementbackend.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
