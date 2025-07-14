package com.example.car_selling.services.customer;

import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.dto.SearchCarDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    boolean createCar(CarDTO carDTO);

    List<CarResponse> getAllCars();

    CarResponse getCarById(Long id);

    void deleteCar(Long id);


    boolean updateCar(Long id, CarDTO carDTO);

    List<CarResponse> searchCar(SearchCarDTO searchCarDTO);

    List<CarResponse> getMyCars(UUID id);
}
