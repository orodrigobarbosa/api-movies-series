package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import com.rodrigo_barbosa.series_filmes_api.domain.service.FilmesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmesController {

    private FilmesService filmesService;

    @PostMapping
    public Filmes salvar(@RequestBody Filmes filmes){
        return filmesService.salvar(filmes);
    }


    @GetMapping
    public List<Filmes> listarFilmes (){
        return filmesService.listarFilmes();
    }
}
