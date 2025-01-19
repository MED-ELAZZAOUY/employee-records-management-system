package io.hahnsoftware.employeemanagementbackend.services;

import io.hahnsoftware.employeemanagementbackend.dtos.UserDto;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserAlreadyExistException;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserNotRoundException;

import java.util.List;

public interface UserService {
    void createUserWithRoles(UserDto userRequestDTO) throws UserAlreadyExistException;

    UserDto updateUser(Long id, UserDto userDto) throws UserNotRoundException;

    void deleteUser(Long id) throws UserNotRoundException;

    UserDto getUser(Long id) throws UserNotRoundException;

    List<UserDto> getAllUsers();
}
