package com.senai.back.dtos.campanhas;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CampanhaInput {
    private String nomeCampanha;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
}
