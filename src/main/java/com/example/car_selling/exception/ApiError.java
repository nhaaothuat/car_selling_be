package com.example.car_selling.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
