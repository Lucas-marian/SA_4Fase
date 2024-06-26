package com.senai.back.dtos.users.profissional;

import com.senai.back.entities.enums.Genero;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ProfissionalInput {
    private String nomeCompleto;
    private String dataNascimento;
    private Genero genero;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true) @Email
    private String email;
    @Size(min = 8)
    private String senha;
    private String numeroCoren;
}
