package io.hahnsoftware.employeemanagementbackend.securities;

import io.hahnsoftware.employeemanagementbackend.entities.Role;
import io.hahnsoftware.employeemanagementbackend.entities.User;
import io.hahnsoftware.employeemanagementbackend.enums.RoleName;
import io.hahnsoftware.employeemanagementbackend.repositories.RoleRepository;
import io.hahnsoftware.employeemanagementbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Configuration
public class Initializer {
    private final PasswordEncoder passwordEncoder;

    public Initializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createRolesAndAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByRoleName(RoleName.ADMIN).isEmpty()) {
                Role adminRole = Role.builder()
                        .roleName(RoleName.ADMIN)
                        .build();
                roleRepository.save(adminRole);
            }

            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                List<Role> roles = List.of(roleRepository.findByRoleName(RoleName.ADMIN).get());

                User adminUser = User.builder()
                        .fullName("Admin")
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("admin123@@"))
                        .roles(roles)
                        .build();

                userRepository.save(adminUser);
                System.out.println("Admin user created: admin@admin.com / admin123@@");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}
