package com.senai.back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeRua;
    private String numeroCasa;
    private String complemento;
    private Number cep;
    private String bairro;
    private String cidade;
    private String estado;
    // caso seja morador de zona rural;
    private String interior;

    @OneToOne(mappedBy = "endereco")
    private User user;

}
