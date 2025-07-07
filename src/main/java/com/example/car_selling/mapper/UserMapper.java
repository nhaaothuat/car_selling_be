package com.example.car_selling.mapper;


import com.example.car_selling.dto.SignUpRequest;
import com.example.car_selling.dto.UserDTO;
import com.example.car_selling.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignUpRequest signUpRequest);

    UserDTO toDTO(User user);

}
