package com.rodrigo_barbosa.series_filmes_api.domain.service;

import com.rodrigo_barbosa.series_filmes_api.domain.handler.HandleAnoNaoEncontrado;
import com.rodrigo_barbosa.series_filmes_api.domain.handler.HandleAtorNaoEncontrado;
import com.rodrigo_barbosa.series_filmes_api.domain.handler.HandleGeneroNaoEncontrado;
import com.rodrigo_barbosa.series_filmes_api.domain.handler.HandleTituloNaoEncontrado;
import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import com.rodrigo_barbosa.series_filmes_api.domain.repository.FilmesRepository;
import feign.Param;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FilmesService {


    private FilmesRepository filmesRepository;
    private ModelMapper modelMapper;

    //POST
    public Filmes salvar(Filmes filmes) {
        return filmesRepository.save(filmes);
    }

    //GET ALL
    public List<Filmes> listarFilmes() {
        return filmesRepository.findAll();
    }

    //GET FILME BY ID
    public Filmes buscarFilmeId(Integer id) {
        return filmesRepository.findById(id).get();

    }

    //PUT
    public Filmes atualizar(Integer id, Filmes filmes) {
        if (filmes.getId() == null) {
            throw new IllegalArgumentException("Filme sem ID");
        }
        Filmes filmeExistente = filmesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));

        // Utilize o ModelMapper para mapear os campos
        modelMapper.map(filmes, filmeExistente);

        return filmesRepository.save(filmeExistente);
    }

    //DELETE
    public void deletarFilmePorId(Integer id) {
        filmesRepository.deleteById(id);
    }


//QUERIES PERSONALIZADAS

    public List<Filmes> buscarPorTitulo(String titulo) {
        List<Filmes> filmes = filmesRepository.buscarPorTitulo(titulo);
        if (filmes.isEmpty()) {
            throw new HandleTituloNaoEncontrado("Nenhum filme encontrada para o nome: " + titulo);
        }
        return filmes;
    }

    public List<Filmes> buscarPorAtor(String ator) {
        List<Filmes> filmes = filmesRepository.buscarPorAtor(ator);

        if (filmes.isEmpty()) {
            throw new HandleAtorNaoEncontrado("Nenhum filme encontrada para a/o ator/atriz: " + ator);
        }
        return filmes;
    }

    public List<Filmes> buscarPorGenero(String genero) {
        List<Filmes> filmes = filmesRepository.buscarPorGenero(genero);

        if (filmes.isEmpty()) {
            throw new HandleGeneroNaoEncontrado("Nenhum filme encontrada para o gênero: " + genero);
        }
        return filmes;
    }

    public List<Filmes> buscarPorAno(Integer ano) {
        List<Filmes> filmes = filmesRepository.buscarPorAno(ano);

        if (filmes.isEmpty()) {
            throw new HandleAnoNaoEncontrado("Nenhum filme encontrada para o ano: " + ano);
        }
        return filmes;
    }

    public List<Filmes> buscarPorRelevancia(String relevancia) {
        List<Filmes> filmes = filmesRepository.buscarPorRelevancia(relevancia);

        if (filmes.isEmpty()) {
            throw new IllegalArgumentException("Nenhum filme encontrada para a relevância: " + relevancia);
        }
        return filmes;
    }

}





