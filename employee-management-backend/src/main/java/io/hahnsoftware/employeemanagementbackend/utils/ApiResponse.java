package io.hahnsoftware.employeemanagementbackend.utils;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
