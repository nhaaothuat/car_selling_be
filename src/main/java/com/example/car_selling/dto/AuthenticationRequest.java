package com.example.car_selling.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;

    private String password;

}
