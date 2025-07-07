package com.example.car_selling.services.auth;

import com.example.car_selling.dto.AuthenticationRequest;
import com.example.car_selling.dto.AuthenticationResponse;
import com.example.car_selling.dto.SignUpRequest;
import com.example.car_selling.dto.UserDTO;
import com.example.car_selling.entity.User;
import com.example.car_selling.enums.UserRole;
import com.example.car_selling.exception.NotFound;
import com.example.car_selling.exception.UserException;
import com.example.car_selling.mapper.UserMapper;
import com.example.car_selling.repositories.UserRepository;
import com.example.car_selling.utils.JWTUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Create successfully");
        } else {
            System.out.println("Account already exist");
        }
    }

    @Override
    public UserDTO signup(SignUpRequest signUpRequest) {
        if (hasUserWithEmail(signUpRequest.getEmail())) {
            throw new UserException("Email already exist");
        }

        User user = userMapper.toEntity(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);


        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public AuthenticationResponse signin(AuthenticationRequest authenticationRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    ));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username and password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());


        final String jwt = jwtUtils.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            response.setJwt(jwt);
            response.setUserRole(optionalUser.get().getUserRole());
            response.setId(optionalUser.get().getId());

        }

        return response;

    }


    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
