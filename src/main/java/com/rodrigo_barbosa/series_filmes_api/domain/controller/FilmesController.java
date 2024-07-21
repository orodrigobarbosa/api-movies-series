package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import com.rodrigo_barbosa.series_filmes_api.domain.service.FilmesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmesController {

    private FilmesService filmesService;

    // Endpoint para listar todos os filmes (público)
    @GetMapping
    public List<Filmes> listarFilmes() {
        return filmesService.listarFilmes();
    }

    // Endpoint para buscar um filme pelo ID (público)
    @GetMapping("/{id}")
    public Filmes buscarFilmePorId(@PathVariable Integer id) {
        return filmesService.buscarFilmeId(id);
    }

    // Endpoint para adicionar um novo filme (protegido, precisa de autenticação)
    @PostMapping
    public Filmes salvar(@RequestBody Filmes filmes) {
        return filmesService.salvar(filmes);
    }

    // Endpoint para atualizar um filme pelo ID (protegido, precisa de autenticação)
    @PutMapping("/atualizar/{id}")
    public Filmes atualizar(@PathVariable Integer id, @RequestBody Filmes filmes) {
        if (!id.equals(filmes.getId())) {
            throw new IllegalArgumentException("O ID do filme no path não corresponde ao ID do filme no corpo da requisição");
        }
        return filmesService.atualizar(id, filmes);
    }

    // Endpoint para deletar um filme pelo ID (protegido, precisa de autenticação)
    @DeleteMapping("/{id}")
    public void deletarFilmePorId(@PathVariable Integer id) {
        filmesService.deletarFilmePorId(id);
    }

    // Consultas personalizadas (público)
    @GetMapping("/buscar/titulo")
    public List<Filmes> filmesPorTitulo(@RequestParam("titulo") String titulo) {
        return filmesService.buscarPorTitulo(titulo);
    }

    @GetMapping("/buscar/ator")
    public List<Filmes> filmesPorAtor(@RequestParam("ator") String ator) {
        return filmesService.buscarPorAtor(ator);
    }

    @GetMapping("/buscar/genero")
    public List<Filmes> filmesPorGenero(@RequestParam("genero") String genero) {
        return filmesService.buscarPorGenero(genero);
    }

    @GetMapping("/buscar/ano")
    public List<Filmes> filmesPorAno(@RequestParam("ano") Integer ano) {
        return filmesService.buscarPorAno(ano);
    }

    @GetMapping("/buscar/relevancia")
    public List<Filmes> filmesPorRelevancia(@RequestParam("relevancia") String relevancia) {
        return filmesService.buscarPorRelevancia(relevancia);
    }
}
