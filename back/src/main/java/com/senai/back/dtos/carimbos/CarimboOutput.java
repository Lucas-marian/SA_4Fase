package com.senai.back.dtos.carimbos;

import java.time.LocalDate;

import com.senai.back.entities.User;
import com.senai.back.entities.Vacinas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarimboOutput {
    private Long id;
    private LocalDate data;
    private User profissional;
    private User user;
    private Vacinas vacinas;
}
