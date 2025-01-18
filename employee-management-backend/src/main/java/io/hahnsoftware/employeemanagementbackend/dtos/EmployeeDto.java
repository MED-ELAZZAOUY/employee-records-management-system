package io.hahnsoftware.employeemanagementbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter @Builder
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Employee ID is required and cannot be blank")
    private String employeeId;
    @NotBlank(message = "Full name is required and cannot be blank")
    private String fullName;
    @NotBlank(message = "Job title is required and cannot be blank")
    private String jobTitle;
    @NotBlank(message = "Department name is required and cannot be blank")
    private String departmentName;
    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;
    @NotBlank(message = "Employment status is required and cannot be blank")
    private String employmentStatus;
    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Phone number is required and cannot be blank")
    @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 digits")
    private String phoneNumber;
    @NotBlank(message = "Address is required and cannot be blank")
    private String address;
}
