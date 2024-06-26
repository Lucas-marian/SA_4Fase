package com.senai.back.dtos.unidadesSaude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UnidadeSaudeInput {
    private String nomeUnidade;
    private String phone;
    private String whatsapp;
    private String nomeRua;
    private String numeroUnidade;
    private Number cep;
    private String bairro;
    private String cidade;
    private String estado;
}
