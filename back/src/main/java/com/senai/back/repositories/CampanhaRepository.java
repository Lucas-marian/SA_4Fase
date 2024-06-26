package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    
}
