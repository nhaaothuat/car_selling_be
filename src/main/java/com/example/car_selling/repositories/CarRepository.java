package com.example.car_selling.repositories;

import com.example.car_selling.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByUserId(UUID id);
}
