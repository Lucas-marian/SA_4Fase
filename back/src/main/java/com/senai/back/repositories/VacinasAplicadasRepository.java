package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.VacinasAplicadas;

public interface VacinasAplicadasRepository extends JpaRepository<VacinasAplicadas, Long> {
    
}
