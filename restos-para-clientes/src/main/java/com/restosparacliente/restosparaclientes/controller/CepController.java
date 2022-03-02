package com.restosparacliente.restosparaclientes.controller;

import com.restosparacliente.restosparaclientes.model.Cep;
import com.restosparacliente.restosparaclientes.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<Cep> getPersonagem(@PathVariable String cep){
            Cep endereco = cepService.getCep(cep);
            return ResponseEntity.ok(endereco);
    }
}