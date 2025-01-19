package io.hahnsoftware.employeemanagementbackend.services.servicesimp;


import io.hahnsoftware.employeemanagementbackend.dtos.AuthenticationResponse;
import io.hahnsoftware.employeemanagementbackend.dtos.LoginRequest;
import io.hahnsoftware.employeemanagementbackend.entities.User;
import io.hahnsoftware.employeemanagementbackend.securities.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        var claims = new HashMap<String, Object>();
        var user = ((User) authentication.getPrincipal());
        claims.put("fullName", user.getFullName());
        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
