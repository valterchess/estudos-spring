package com.micro.restfullmicro.controllers;

import com.micro.restfullmicro.models.Produto;
import com.micro.restfullmicro.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(){
        var produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {return ResponseEntity.notFound().build();}
         else{
             for (Produto produto : produtos) {
                var id = produto.getId();
                produto.add(linkTo(methodOn(ProdutoController.class).getById(id)).withSelfRel());
             }
             return ResponseEntity.ok(produtos);
         }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable long id){
        var produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            produto
                    .get()
                    .add(linkTo(methodOn(ProdutoController.class)
                            .getAllProdutos())
                            .withRel("Lista de Produtos"));
            return ResponseEntity.ok(produto.get());
        }
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto produto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoRepository.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable long id, @Valid @RequestBody Produto produto){
        var produto1 = produtoRepository.findById(id);
        return (produto1.isPresent())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

}
