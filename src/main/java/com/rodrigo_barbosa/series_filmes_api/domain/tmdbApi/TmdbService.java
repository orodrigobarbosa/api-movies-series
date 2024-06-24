package com.rodrigo_barbosa.series_filmes_api.domain.tmdbApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TmdbService {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    private final String TMDB_API_URL = "https://api.themoviedb.org/3/search/movie";
    private final RestTemplate restTemplate;

    public TmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TmdbDTO> searchMovies(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TMDB_API_URL)
                .queryParam("api_key", tmdbApiKey)
                .queryParam("query", query);

        TmdbResposta response = restTemplate.getForObject(builder.toUriString(), TmdbResposta.class);
        return response.getResults();
    }
}
