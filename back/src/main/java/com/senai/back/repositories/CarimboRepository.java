package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.Carimbo;

public interface CarimboRepository extends JpaRepository<Carimbo, Long> {
    
}
