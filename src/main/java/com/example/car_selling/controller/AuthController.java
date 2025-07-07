package com.example.car_selling.controller;


import com.example.car_selling.dto.AuthenticationRequest;
import com.example.car_selling.dto.AuthenticationResponse;
import com.example.car_selling.dto.SignUpRequest;
import com.example.car_selling.dto.UserDTO;
import com.example.car_selling.services.auth.AuthService;
import com.example.car_selling.utils.JWTUtils;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        UserDTO userDTO = authService.signup(signUpRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authService.signin(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
