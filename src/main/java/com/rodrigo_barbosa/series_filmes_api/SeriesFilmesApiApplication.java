package com.rodrigo_barbosa.series_filmes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeriesFilmesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeriesFilmesApiApplication.class, args);
	}

}
