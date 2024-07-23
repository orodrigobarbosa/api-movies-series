package com.rodrigo_barbosa.series_filmes_api.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String cpf; // CPF do usuário para login
    private String senha; // Senha do usuário para login
}
