package com.learning.ranga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.ranga.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
