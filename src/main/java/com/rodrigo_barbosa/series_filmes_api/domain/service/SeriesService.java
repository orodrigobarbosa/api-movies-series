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

    public Series buscarPorId(Integer id) {
        return seriesRepository.findById(id).get();
    }


    public void deletarSeriePorId(Integer id) {
        seriesRepository.deleteById(id);
    }

    public Series atualizarSerie(Integer id, Series serie) {
        if (serie.getId() == null) {
            throw new IllegalArgumentException("Série sem ID");
        }
        Series serieExistente = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Serie não encontrada"));
        modelMapper.map(serie, serieExistente);
        return seriesRepository.save(serie);
    }


    //QUERIES PERSONALIZADAS


    public List<Series> buscarSeriePorTitulo(String titulo) {
        List<Series> series = seriesRepository.seriesPorTitulo(titulo);

        if (series.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma série encontrada para o título: " + titulo);
        }
        return series;
    }

    public List<Series> buscarSeriePorGenero(String genero) {
        List<Series> series = seriesRepository.seriesPorGenero(genero);

        if (series.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma série encontrada para o gênero: " + genero);
        }
        return series;
    }

    public List<Series> buscarSeriePorAno(Integer ano) {
        List<Series> series = seriesRepository.seriesPorAno(ano);

        if (series.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma série encontrada para o ano: " + ano);
        }
        return series;
    }

}
