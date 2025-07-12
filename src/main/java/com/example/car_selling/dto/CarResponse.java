package com.example.car_selling.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Data
public class CarResponse {

    private Long id;

    private String name;

    private String brand;

    private String type;

    private String transmission;

    private String color;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date year;

    private Boolean sold;

    private String description;
    private Long price;





    private UUID userId;

    private byte[] img;

}
