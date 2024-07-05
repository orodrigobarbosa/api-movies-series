package com.rodrigo_barbosa.series_filmes_api.domain.repository;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Integer> {

    @Query("select f from Filmes f where f.titulo = :titulo")
    List<Filmes> buscarPorTitulo(String titulo);

    @Query("select f from Filmes f where f.ator = :ator")
    List<Filmes> buscarPorAtor(String ator);

    @Query("select f from Filmes f where f.genero = :genero")
    List<Filmes> buscarPorGenero(String genero);

    @Query("select f from Filmes f where f.ano = :ano")
    List<Filmes> buscarPorAno(Integer ano);

    @Query("select  f from Filmes f where f.relevancia = :relevancia")
    List<Filmes> buscarPorRelevancia(String relevancia);

}
