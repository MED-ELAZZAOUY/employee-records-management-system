package io.hahnsoftware.employeemanagementbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementBackendApplication.class, args);
    }

}
