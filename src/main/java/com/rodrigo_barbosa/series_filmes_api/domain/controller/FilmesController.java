package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import com.rodrigo_barbosa.series_filmes_api.domain.repository.FilmesRepository;
import com.rodrigo_barbosa.series_filmes_api.domain.service.FilmesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmesController {

    private final FilmesRepository filmesRepository;
    private FilmesService filmesService;

    @PostMapping
    public Filmes salvar(@RequestBody Filmes filmes) {
        return filmesService.salvar(filmes);
    }


    @GetMapping
    public List<Filmes> listarFilmes() {
        return filmesService.listarFilmes();
    }


    @GetMapping("/{id}")
    public Filmes buscarFilmePorId(@PathVariable Integer id) {
        return filmesService.buscarFilmeId(id);
    }

    @PutMapping("/atualizar/{id}")
    public Filmes atualizar(@PathVariable Integer id, @RequestBody Filmes filmes) {
        if (!id.equals(filmes.getId())) {
            throw new IllegalArgumentException("O ID do filme no path não corresponde ao ID do filme no corpo da requisição");
        }
        return filmesService.atualizar(id, filmes);
    }


    @DeleteMapping("/{id}")
    public void deletarFilmePorId(@PathVariable Integer id) {
        filmesService.deletarFilmePorId(id);
    }

    //consultas personalizadas
    @GetMapping("/filmes/titulo")
    public List<Filmes> filmesPorTitulo(@RequestParam("titulo") String titulo) {
        return filmesRepository.buscarPorTitulo(titulo);
    }
}
