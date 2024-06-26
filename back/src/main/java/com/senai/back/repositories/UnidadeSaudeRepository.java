package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.UnidadeSaude;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {
    
}
