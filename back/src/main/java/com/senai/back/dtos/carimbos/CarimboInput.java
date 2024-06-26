package com.senai.back.dtos.carimbos;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarimboInput {
    private LocalDate data;
    private String lote;
    private String emailuser;
}
