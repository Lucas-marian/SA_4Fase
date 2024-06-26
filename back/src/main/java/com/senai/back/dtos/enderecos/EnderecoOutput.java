package com.senai.back.dtos.enderecos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class EnderecoOutput {
    private Long id;
    private String nomeRua;
    private String numeroCasa;
    private String complemento;
    private Number cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String interior;
}
