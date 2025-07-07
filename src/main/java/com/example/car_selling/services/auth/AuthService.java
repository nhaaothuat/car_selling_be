package com.example.car_selling.services.auth;

import com.example.car_selling.dto.AuthenticationRequest;
import com.example.car_selling.dto.AuthenticationResponse;
import com.example.car_selling.dto.SignUpRequest;
import com.example.car_selling.dto.UserDTO;

public interface AuthService {

    UserDTO signup(SignUpRequest signUpRequest);

    AuthenticationResponse signin(AuthenticationRequest authenticationRequest);

    Boolean hasUserWithEmail(String email);
}
