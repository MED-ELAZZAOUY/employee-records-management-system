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
    public CommandLineRunner createRolesAndUsers(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByRoleName(RoleName.ADMIN).isEmpty()) {
                Role adminRole = Role.builder()
                        .roleName(RoleName.ADMIN)
                        .build();
                roleRepository.save(adminRole);
            }

            if (roleRepository.findByRoleName(RoleName.HR).isEmpty()) {
                Role hrRole = Role.builder()
                        .roleName(RoleName.HR)
                        .build();
                roleRepository.save(hrRole);
            }

            if (roleRepository.findByRoleName(RoleName.MANAGER).isEmpty()) {
                Role managerRole = Role.builder()
                        .roleName(RoleName.MANAGER)
                        .build();
                roleRepository.save(managerRole);
            }


            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                Role adminRole = roleRepository.findByRoleName(RoleName.ADMIN).get();

                User adminUser = User.builder()
                        .fullName("Admin User")
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("admin123@@"))
                        .roles(List.of(adminRole))
                        .build();

                userRepository.save(adminUser);
                System.out.println("Admin user created: admin@admin.com / admin123@@");
            } else {
                System.out.println("Admin user already exists.");
            }

            if (userRepository.findByEmail("hr@hr.com").isEmpty()) {
                Role hrRole = roleRepository.findByRoleName(RoleName.HR).get();

                User hrUser = User.builder()
                        .fullName("HR User")
                        .email("hr@hr.com")
                        .password(passwordEncoder.encode("hr123@@"))
                        .roles(List.of(hrRole))
                        .build();

                userRepository.save(hrUser);
                System.out.println("HR user created: hr@hr.com / hr123@@");
            } else {
                System.out.println("HR user already exists.");
            }

            if (userRepository.findByEmail("manager@manager.com").isEmpty()) {
                Role managerRole = roleRepository.findByRoleName(RoleName.MANAGER).get();

                User managerUser = User.builder()
                        .fullName("Manager User")
                        .email("manager@manager.com")
                        .password(passwordEncoder.encode("manager123@@"))
                        .roles(List.of(managerRole))
                        .build();

                userRepository.save(managerUser);
                System.out.println("Manager user created: manager@manager.com / manager123@@");
            } else {
                System.out.println("Manager user already exists.");
            }
        };
    }
}
