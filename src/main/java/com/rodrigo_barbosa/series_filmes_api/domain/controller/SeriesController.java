package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Series;
import com.rodrigo_barbosa.series_filmes_api.domain.service.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/series")
public class SeriesController {

    private SeriesService seriesService;

    // Endpoint para listar todas as séries (público)
    @GetMapping
    public List<Series> listarSeries() {
        return seriesService.listarSeries();
    }

    // Endpoint para buscar uma série pelo ID (público)
    @GetMapping("/{id}")
    public Series buscarSeriePorId(@PathVariable Integer id) {
        return seriesService.buscarPorId(id);
    }

    // Endpoint para adicionar uma nova série (protegido, precisa de autenticação)
    @PostMapping("/cadastrar")
    public Series cadastrarSerie(@RequestBody Series serie) {
        return seriesService.cadastrarSerie(serie);
    }

    // Endpoint para atualizar uma série pelo ID (protegido, precisa de autenticação)
    @PutMapping("/atualizar/{id}")
    public Series atualizarSeriePorId(@PathVariable Integer id, @RequestBody Series serie) {
        if (!id.equals(serie.getId())) {
            throw new IllegalArgumentException("O ID da série no path não corresponde ao ID da série no corpo da requisição");
        }
        return seriesService.atualizarSerie(id, serie);
    }

    // Endpoint para deletar uma série pelo ID (protegido, precisa de autenticação)
    @DeleteMapping("/{id}")
    public void deletarSerie(@PathVariable Integer id) {
        seriesService.deletarSeriePorId(id);
    }

    // Consultas personalizadas (público)
    @GetMapping("/buscar/titulo")
    public List<Series> seriesPorTitulo(@RequestParam("titulo") String titulo) {
        return seriesService.buscarSeriePorTitulo(titulo);
    }

    @GetMapping("/buscar/genero")
    public List<Series> seriesPorGenero(@RequestParam("genero") String genero) {
        return seriesService.buscarSeriePorGenero(genero);
    }

    @GetMapping("/buscar/ano")
    public List<Series> seriesPorAno(@RequestParam("ano") Integer ano) {
        return seriesService.buscarSeriePorAno(ano);
    }
}
