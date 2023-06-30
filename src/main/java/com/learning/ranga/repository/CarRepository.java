package com.learning.ranga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.ranga.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
