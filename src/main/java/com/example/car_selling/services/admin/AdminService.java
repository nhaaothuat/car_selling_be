package com.example.car_selling.services.admin;

import com.example.car_selling.dto.CarResponse;

import java.util.List;

public interface AdminService {

    List<CarResponse> getCars();

    CarResponse getCarById(Long id);

    void deleteCar(Long id);
}
