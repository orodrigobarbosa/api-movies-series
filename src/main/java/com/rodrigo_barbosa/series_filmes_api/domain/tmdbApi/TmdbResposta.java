package com.rodrigo_barbosa.series_filmes_api.domain.tmdbApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbResposta {


    //classe que lida com a busca

    @JsonProperty("results")
    private List<TmdbDTO> results;

    public List<TmdbDTO> getResults() {
        return results;
    }

    public void setResults(List<TmdbDTO> results) {
        this.results = results;
    }
}
