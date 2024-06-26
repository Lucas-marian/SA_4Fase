package com.senai.back.dtos.users.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResetSenhaDTO {
    private String emailUser;
    private String newPassword;
}
