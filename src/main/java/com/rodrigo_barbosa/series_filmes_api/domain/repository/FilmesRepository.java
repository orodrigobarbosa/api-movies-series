package com.rodrigo_barbosa.series_filmes_api.domain.repository;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Integer> {

    @Query("select f from Filmes f where f.titulo = :titulo")
    List<Filmes> buscarPorTitulo(@Param("titulo") String titulo);

    @Query("select f from Filmes f where f.ator = :ator")
    List<Filmes> buscarPorAtor(@Param("ator") String ator);

    @Query("select f from Filmes f where f.genero = :genero")
    List<Filmes> buscarPorGenero(@Param("genero") String genero);

    @Query("select f from Filmes f where f.ano = :ano")
    List<Filmes> buscarPorAno(@Param("ano") Integer ano);

    @Query("select  f from Filmes f where f.relevancia = :relevancia")
    List<Filmes> buscarPorRelevancia(@Param("relevancia") String relevancia);

}
