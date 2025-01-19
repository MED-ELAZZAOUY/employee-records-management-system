package io.hahnsoftware.employeemanagementbackend.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class LoginRequest {
    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Email is not formatted correctly")
    private String email;
    @NotBlank(message = "Password is required and cannot be blank")
    private String password;
}
