package com.example.car_selling.controller;


import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.dto.SearchCarDTO;
import com.example.car_selling.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok(customerService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        customerService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id,@ModelAttribute CarDTO carDTO){
        boolean updated = customerService.updateCar(id, carDTO);
        if(updated){
            return ResponseEntity.ok("Updated");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

    @PostMapping("/car/search")
    public ResponseEntity<List<CarResponse>> searchCars(@RequestBody SearchCarDTO searchCarDTO){
        return ResponseEntity.ok(customerService.searchCar(searchCarDTO));
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<List<CarResponse>> getMyCars(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.getMyCars(id));
    }
}
