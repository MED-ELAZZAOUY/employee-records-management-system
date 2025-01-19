package io.hahnsoftware.employeemanagementbackend.services.servicesimp;

import io.hahnsoftware.employeemanagementbackend.dtos.UserDto;
import io.hahnsoftware.employeemanagementbackend.entities.Role;
import io.hahnsoftware.employeemanagementbackend.entities.User;
import io.hahnsoftware.employeemanagementbackend.enums.RoleName;
import io.hahnsoftware.employeemanagementbackend.exceptions.RoleNotFoundException;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserAlreadyExistException;
import io.hahnsoftware.employeemanagementbackend.exceptions.UserNotRoundException;
import io.hahnsoftware.employeemanagementbackend.repositories.RoleRepository;
import io.hahnsoftware.employeemanagementbackend.repositories.UserRepository;
import io.hahnsoftware.employeemanagementbackend.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUserWithRoles(UserDto userDto) throws UserAlreadyExistException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("A user with this email already exists: " + userDto.getEmail());
        }

        Set<Role> roles = fetchRoles(userDto.getRoles());

        User user = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(new ArrayList<>(roles))
                .build();

        userRepository.save(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws UserNotRoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotRoundException("User not found with id: " + id));

        existingUser.setFullName(userDto.getFullName());
        existingUser.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        existingUser.setRoles(new ArrayList<>(fetchRoles(userDto.getRoles())));

        userRepository.save(existingUser);

        return UserDto.builder()
                .fullName(existingUser.getFullName())
                .email(existingUser.getEmail())
                .roles(existingUser.getRoles().stream()
                        .map(role -> role.getRoleName().name())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void deleteUser(Long id) throws UserNotRoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotRoundException("User not found with id: " + id));

        user.getRoles().clear();

        userRepository.delete(user);
    }

    @Override
    public UserDto getUser(Long id) throws UserNotRoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotRoundException("User not found with id: " + id));

        return UserDto.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRoleName().name())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .roles(user.getRoles().stream()
                                .map(role -> role.getRoleName().name())
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }

    private Set<Role> fetchRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(roleName -> roleRepository.findByRoleName(RoleName.valueOf(roleName.toUpperCase()))
                        .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
    }
}
