package com.senai.back.dtos.vacinasAplicadas;

import java.time.LocalDateTime;

import com.senai.back.entities.User;
import com.senai.back.entities.Vacinas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VacinasAplicadasOutput {
    private Long id;
    private LocalDateTime dataAplicação;
    private String quantidadeDoses;
    private String dataRetorno;
    private User user;
    private User profissional;
    private Vacinas vacina;
}
