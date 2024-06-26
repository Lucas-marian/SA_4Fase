package com.senai.back.dtos.users.profissional;

import com.senai.back.entities.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProfissionalOutput {
    private Long id;
    private String nomeCompleto;
    private String dataNascimento;
    private Genero genero;
    private String email;
    private String numeroCoren;

}
