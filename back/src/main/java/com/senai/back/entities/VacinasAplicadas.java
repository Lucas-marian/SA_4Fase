package com.senai.back.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class VacinasAplicadas {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dataAplicação;
    private String quantidadeDoses;
    private String dataRetorno;

    @OneToOne
    private User user;
    @OneToOne
    private User profissional;
    @OneToOne
    private Vacinas vacina;
    
}
