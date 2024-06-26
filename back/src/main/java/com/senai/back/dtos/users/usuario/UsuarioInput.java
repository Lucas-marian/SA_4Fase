package com.senai.back.dtos.users.usuario;

import com.senai.back.entities.enums.Genero;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class UsuarioInput {
    private String nomeCompleto;
    private String dataNascimento;
    private Genero genero;
    private String cpf;
    @Column(unique = true) @Email
    private String email;
    @Size(min = 8)
    private String senha;

}
