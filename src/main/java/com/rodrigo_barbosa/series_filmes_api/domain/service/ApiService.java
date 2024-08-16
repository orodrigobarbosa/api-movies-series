package com.rodrigo_barbosa.series_filmes_api.domain.service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class ApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    public void callProtectedEndpoint(String token) {
        String url = "http://localhost:8080/endpoint-protegido";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
    }
}
//Service responsável por chamar um endpoint protegido de uma API