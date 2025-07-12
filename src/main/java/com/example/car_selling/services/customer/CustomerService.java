package com.example.car_selling.services.customer;

import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;

import java.util.List;

public interface CustomerService {

    boolean createCar(CarDTO carDTO);

    List<CarResponse> getAllCars();
}
