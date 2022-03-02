package com.restosparacliente.restosparaclientes.controller;

import com.restosparacliente.restosparaclientes.model.Personagem;
import com.restosparacliente.restosparaclientes.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @GetMapping()
    public ResponseEntity<List<Personagem>> getPersonagem(){
        List<Personagem> personagens = new ArrayList<>();
        for (long i = 1; i < 1000; i++){
            Personagem personagem = personagemService.getById(i);
            if(personagem != null) {
                personagens.add(personagem);
            }
        }
        return ResponseEntity.ok(personagens);
    }
}