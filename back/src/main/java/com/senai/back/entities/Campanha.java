package com.senai.back.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Campanha {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeCampanha;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

}
