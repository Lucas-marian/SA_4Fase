package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.Vacinas;

public interface VacinasRetository extends JpaRepository<Vacinas, Long> {
    Vacinas findByLote(String lote);
}
