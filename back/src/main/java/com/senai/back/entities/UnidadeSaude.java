package com.senai.back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class UnidadeSaude {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeUnidade;
    private String phone;
    private String whatsapp;
    private String nomeRua;
    private String numeroUnidade;
    private Number cep;
    private String bairro;
    private String cidade;
    private String estado;

}
