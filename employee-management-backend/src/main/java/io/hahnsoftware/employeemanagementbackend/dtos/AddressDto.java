package io.hahnsoftware.employeemanagementbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class AddressDto {
    @NotBlank(message = "Street is required and cannot be blank")
    private String street;
    @NotBlank(message = "City is required and cannot be blank")
    private String city;
    @NotBlank(message = "State is required and cannot be blank")
    private String state;
    @NotBlank(message = "Postal code is required and cannot be blank")
    private String postalCode;
    @NotBlank(message = "Country is required and cannot be blank")
    private String country;
}
