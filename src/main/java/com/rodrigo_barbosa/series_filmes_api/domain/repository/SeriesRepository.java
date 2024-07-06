package com.rodrigo_barbosa.series_filmes_api.domain.repository;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Series;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository <Series, Integer> {

    @Query("select s from Series s where s.titulo = :titulo")
    List<Series> seriesPorTitulo(@Param("titulo") String titulo);


    @Query("select s from Series s where s.genero = : genero")
    List<Series> seriesPorGenero(@Param("genero") String genero);

    @Query("select  s from Series s where s.ano = :ano")
    List<Series> seriesPorAno(@Param("ano") Integer ano);
}
