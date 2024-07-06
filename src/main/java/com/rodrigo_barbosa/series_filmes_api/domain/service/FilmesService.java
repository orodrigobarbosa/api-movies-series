package com.rodrigo_barbosa.series_filmes_api.domain.service;

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
            throw new IllegalArgumentException("Filme sem titulo");
        }
        return filmes;
    }

    public List<Filmes> buscarPorAtor(String ator) {
        List<Filmes> filmes = filmesRepository.buscarPorAtor(ator);
        
        if (filmes.isEmpty()) {
            throw new IllegalArgumentException("Informe nome de um ator ou atriz");
        }
        return filmes;
    }

    public List<Filmes> buscarPorGenero(String genero) {
        List<Filmes> filmes = filmesRepository.buscarPorGenero(genero);

        if (filmes.isEmpty()) {
            throw new IllegalArgumentException("Informe um gênero");
        }
        return filmes;
    }

    public List<Filmes> buscarPorAno(Integer ano) {
        List<Filmes> filmes = filmesRepository.buscarPorAno(ano);

        if (filmes.isEmpty()) {
            throw new IllegalArgumentException("Informe ano");
        }
        return filmes;
    }

    public List<Filmes> buscarPorRelevancia(String relevancia) {
        List<Filmes> filmes = filmesRepository.buscarPorRelevancia(relevancia);

        if (filmes.isEmpty()) {
            throw new IllegalArgumentException("Informe  relevancia");
        }
        return filmes;
    }

}





