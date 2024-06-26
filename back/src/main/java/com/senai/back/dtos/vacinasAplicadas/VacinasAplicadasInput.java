package com.senai.back.dtos.vacinasAplicadas;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VacinasAplicadasInput {
    private String emailUser;
    private String loteVacina;
    private LocalDateTime dataAplicação;
    private String quantidadeDoses;
    private String dataRetorno;
}
