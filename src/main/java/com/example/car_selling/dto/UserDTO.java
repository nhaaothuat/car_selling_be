package com.example.car_selling.dto;

import com.example.car_selling.enums.UserRole;
import lombok.Data;


import java.util.UUID;


@Data
public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private UserRole userRole;

}
