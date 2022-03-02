package com.restosparacliente.restosparaclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RestosParaClientesApplication {

	@Bean
	public WebClient webClientRick(WebClient.Builder builder){
		return builder.baseUrl("https://rickandmortyapi.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Bean
	public WebClient webClientCep(WebClient.Builder builder){
		return builder.baseUrl("https://viacep.com.br")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestosParaClientesApplication.class, args);
	}

}
