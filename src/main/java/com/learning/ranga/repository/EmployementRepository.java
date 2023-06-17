package com.learning.ranga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.ranga.entity.Employement;

public interface EmployementRepository extends JpaRepository<Employement, Long> {

}
