package io.hahnsoftware.employeemanagementbackend.controllers;


import io.hahnsoftware.employeemanagementbackend.dtos.UserDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserAlreadyExistException;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserNotRoundException;
import io.hahnsoftware.employeemanagementbackend.services.UserService;
import io.hahnsoftware.employeemanagementbackend.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException {
        userService.createUserWithRoles(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("User created successfully", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) throws UserNotRoundException {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(new ApiResponse<>("User updated successfully", updatedUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotRoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id) throws UserNotRoundException {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(new ApiResponse<>("User fetched successfully", user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<UserDto> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Users fetched successfully", users));
    }
}
