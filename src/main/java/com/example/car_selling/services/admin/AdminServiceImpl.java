package com.example.car_selling.services.admin;

import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.mapper.CustomerMapper;
import com.example.car_selling.repositories.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CarRepository carRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CarResponse> getCars() {
        return carRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarResponse getCarById(Long id) {
        return carRepository.findById(id)
                .map(customerMapper::toDTO).orElse(null);
    }

    @Override
    public void deleteCar(Long id) {
       if(!carRepository.existsById(id)) throw new EntityNotFoundException("Not found id "+ id);
       carRepository.deleteById(id);
    }
}
