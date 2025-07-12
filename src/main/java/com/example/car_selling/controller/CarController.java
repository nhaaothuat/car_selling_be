package com.example.car_selling.controller;


import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CarController {

    private final CustomerService customerService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@ModelAttribute CarDTO carDTO) throws  IOException{

        boolean success = customerService.createCar(carDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/get-car")
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok(customerService.getAllCars());
    }



}
