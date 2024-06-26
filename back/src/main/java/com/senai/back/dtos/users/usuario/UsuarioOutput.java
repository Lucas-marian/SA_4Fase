package com.senai.back.dtos.users.usuario;

import com.senai.back.entities.enums.Categoria;
import com.senai.back.entities.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UsuarioOutput {
    private Long id;
    private String nomeCompleto;
    private String dataNascimento;
    private Genero genero;
    private String cpf;
    private String email;
    private Categoria categoria;
}
