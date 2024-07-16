package com.rodrigo_barbosa.series_filmes_api.domain.model;

import com.rodrigo_barbosa.series_filmes_api.domain.validator.ValidGenero;
import com.rodrigo_barbosa.series_filmes_api.domain.validator.ValidRelevancia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String diretor;

    @NotBlank
    @ValidGenero
    private String genero;

    @NotNull
    @Min(1888)//ano quefoi feito o primeiro filmes
    @Max(2100)//limite arbitrário para anos futuros
    private Integer ano;

    @NotNull
    @Min(1)
    @Max(300)  // Supondo que a duração máxima de um filme é 300 minutos
    private Integer duracao;

    @NotBlank
    @ValidRelevancia
    private String relevancia;

    @NotBlank
    private String sinopse;

    private String trailer;
    private String ator;
    private String atriz;
}
