package com.rodrigo_barbosa.series_filmes_api.domain.service;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import com.rodrigo_barbosa.series_filmes_api.domain.repository.FilmesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FilmesService  {
    private FilmesRepository filmesRepository;

    //POST
  public Filmes salvar(Filmes filmes){
      return filmesRepository.save(filmes);
  }
     //GET ALL
    public List<Filmes> listarFilmes(){
      return filmesRepository.findAll();
    }
}
