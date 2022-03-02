package com.restosparacliente.restosparaclientes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restosparacliente.restosparaclientes.model.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonagemService {
    @Autowired
    private WebClient webClientRick;

    public Personagem getById(long id){
        Mono<Personagem> caracter =
                this.webClientRick.method(HttpMethod.GET)
                        .uri("/api/episode/{id}", id)
                        .retrieve()
                        .bodyToMono(Personagem.class);
        return caracter.block();
    }
}