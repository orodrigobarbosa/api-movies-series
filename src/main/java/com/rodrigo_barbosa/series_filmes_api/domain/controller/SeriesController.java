package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Series;
import com.rodrigo_barbosa.series_filmes_api.domain.service.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private SeriesService seriesService;


    @PostMapping("/cadastrar")
    public Series cadastrarSerie(Series serie){
        return seriesService.cadastrarSerie(serie);
    }


    @GetMapping
    public List<Series> listarSeries(){
        return seriesService.listarSeries();
    }

    @GetMapping("/{id}")
    public Series buscarSeriePorId(@PathVariable  Integer id){
        return seriesService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarSerie(@PathVariable Integer id){
        seriesService.deletarSeriePorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public Series atualizarSeriePorId(@PathVariable Integer id, Series serie){
        if (!id.equals(serie.getId())) {
            throw new IllegalArgumentException("O ID da serie no path não corresponde ao ID da serie no corpo da requisição");
        }
        return seriesService.atualizarSerie(id, serie);
    }
}
