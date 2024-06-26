package com.senai.back.dtos.users.usuario;

import com.senai.back.entities.enums.Categoria;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class UpdateUserDTO {
    private String nomeCompleto;
    @Size(min = 8)
    private String senha;
    private Categoria categoria;
}
