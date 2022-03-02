package com.restosparacliente.restosparaclientes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restosparacliente.restosparaclientes.model.Cep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CepService {
    @Autowired
    private WebClient webClientCep;

    public Cep getCep(String cep){
        Mono<Cep> endereco =
                this.webClientCep.method(HttpMethod.GET)
                        .uri("/ws/{cep}/json/unicode/", cep)
                        .retrieve()
                        .bodyToMono(Cep.class);
        return endereco.block();
    }
}