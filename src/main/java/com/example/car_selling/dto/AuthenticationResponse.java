package com.example.car_selling.dto;

import com.example.car_selling.enums.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthenticationResponse {

    private UUID id;

    private String jwt;
    private UserRole userRole;
}
