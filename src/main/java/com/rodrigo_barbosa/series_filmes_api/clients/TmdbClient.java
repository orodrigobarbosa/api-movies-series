package com.rodrigo_barbosa.series_filmes_api.clients;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//API PARA CONSULTAR FILMES
@FeignClient(name = "filmeslib", url = "https://api.themoviedb.org/3/search/movie?query=+movieTitle+\"language=pt-BR")
public interface TmdbClient {


    @GetMapping("/{movie}/json")
    public Filmes consultaTmdb(@PathVariable String query);



}
