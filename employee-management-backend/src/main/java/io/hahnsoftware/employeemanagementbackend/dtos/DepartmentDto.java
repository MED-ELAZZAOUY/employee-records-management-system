package io.hahnsoftware.employeemanagementbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class DepartmentDto {
    private Long id;
    @NotBlank(message = "Department name is required")
    private String name;
}
