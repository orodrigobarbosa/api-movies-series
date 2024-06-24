package com.rodrigo_barbosa.series_filmes_api.domain.repository;

import com.rodrigo_barbosa.series_filmes_api.domain.model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Integer> {
}
