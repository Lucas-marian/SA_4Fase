package com.senai.back.dtos.users.profissional;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class UpdateProfissionalDTO {
    private String nomeCompleto;
    @Size(min = 8)
    private String senha;
}
