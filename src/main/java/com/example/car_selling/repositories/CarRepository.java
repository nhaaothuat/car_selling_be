package com.example.car_selling.repositories;

import com.example.car_selling.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
