package com.senai.back.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Vacinas {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeVacina;
    @Column(unique = true)
    private String lote;
}
