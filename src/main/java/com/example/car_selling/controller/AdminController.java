package com.example.car_selling.controller;


import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.repositories.CarRepository;
import com.example.car_selling.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final CarRepository carRepository;

    private final AdminService adminService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponse>> getAllCars(){
        return ResponseEntity.ok(adminService.getCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }
}
