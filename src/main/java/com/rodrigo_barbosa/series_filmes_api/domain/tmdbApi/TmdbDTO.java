package com.rodrigo_barbosa.series_filmes_api.domain.tmdbApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmdbDTO {
    //DTO para selecionar e separar os dados dos filmes


    private String title;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    @JsonProperty("release_date")
    private String releaseDate;

    private Integer runtime;

    private Double popularity;

    private String overview;

    private Boolean video;

    private Integer id;
}
