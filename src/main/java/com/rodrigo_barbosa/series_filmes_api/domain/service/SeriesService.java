package com.rodrigo_barbosa.series_filmes_api.domain.service;


import com.rodrigo_barbosa.series_filmes_api.domain.model.Series;
import com.rodrigo_barbosa.series_filmes_api.domain.repository.SeriesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;
    private ModelMapper modelMapper;

    public Series cadastrarSerie(Series serie) {
        return seriesRepository.save(serie);
    }

    public List<Series> listarSeries() {
        return seriesRepository.findAll();
    }

    public Series buscarPorId(Integer id){
        return seriesRepository.findById(id).get();
    }


    public void deletarSeriePorId(Integer id) {
        seriesRepository.deleteById(id);
    }

    public Series atualizarSerie(Integer id, Series serie){
        if (serie.getId()==null){
            throw new IllegalArgumentException("Série sem ID");
        }
        Series serieExistente = seriesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Serie não encontrada"));
        modelMapper.map(serie, serieExistente);
        return  seriesRepository.save(serie);
    }
}
