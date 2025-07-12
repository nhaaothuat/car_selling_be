package com.example.car_selling.services.customer;


import com.example.car_selling.dto.CarDTO;
import com.example.car_selling.dto.CarResponse;
import com.example.car_selling.entity.Car;
import com.example.car_selling.entity.User;
import com.example.car_selling.mapper.CustomerMapper;
import com.example.car_selling.repositories.CarRepository;
import com.example.car_selling.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSeviceImpl implements CustomerService{

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    private final CustomerMapper customerMapper;

    @Override
    public boolean createCar(CarDTO carDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findFirstByEmail(username)
                .map(user -> {
                    Car car = customerMapper.toEntity(carDTO);
                    car.setUser(user);
                    carRepository.save(car);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }


}
