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
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titulo;

    @NotNull
    private  Integer temporadas;

    @NotBlank
    @ValidGenero
    private String genero;

    @NotBlank
    @ValidRelevancia
    private String relevancia;

    @NotBlank
    private String diretor;

    @NotNull
    @Min(1888)
    @Max(2100)
    private Integer ano;

}
