package io.hahnsoftware.employeemanagementbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter @Getter @Builder
public class UserDto {
    @NotBlank(message = "Full name is required and cannot be blank")
    private String fullName;
    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Email is not formatted correctly")
    private String email;
    @NotBlank(message = "Password is required and cannot be blank")
    private String password;
    @NotNull(message = "role is mandatory, At least one role must be assigned")
    private Set<String> roles;
}
