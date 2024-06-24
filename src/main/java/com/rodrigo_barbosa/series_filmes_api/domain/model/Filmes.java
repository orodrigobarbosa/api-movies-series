package com.rodrigo_barbosa.series_filmes_api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String genero;
    private Integer ano;
    private Integer duracao;
    private String relevancia;
    private String sinopse;
    private String trailer;
    private String ator;
}
