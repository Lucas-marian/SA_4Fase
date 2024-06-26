package com.senai.back.dtos.vacinas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class VacinasOutput {
    private Long id;
    private String nomeVacina;
    private String lote;
}
