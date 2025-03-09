package com.fiap.myapp.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.myapp.models.Produto;

@RestController
@RequestMapping("/uri-exemplos")
public class UriBuilderController {
    // Lista de produtos em memória
    private List<Produto> produtos = List.of(
        new Produto(1, "Notebook", 2500.0),
        new Produto(2, "Smartphone", 1500.0),
        new Produto(3, "Tablet", 1000.0)
    );

    // Exemplo de UriComponentsBuilder
    @GetMapping("/uricomponents")
    public ResponseEntity<String> exemploUriComponentsBuilder() {
        URI uri = UriComponentsBuilder.fromUriString("https://exemplo.com/produtos/{id}")
                .queryParam("categoria", "{categoria}")
                .buildAndExpand(1, "Eletrônicos")
                .toUri();
        
        return ResponseEntity.ok("URI Construída: " + uri.toString());
    }

    // Exemplo de ServletUriComponentsBuilder
    @GetMapping("/servleturi")
    public ResponseEntity<String> exemploServletUriComponentsBuilder() {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/detalhes")
                .build()
                .toUri();
        
        return ResponseEntity.ok("URI Construída: " + uri.toString());
    }

    // Exemplo de busca de produto com URI dinâmica
    @GetMapping("/produtos")
    public ResponseEntity<List<String>> gerarLinksParaProdutos() {
        List<String> produtoLinks = produtos.stream()
            .map(produto -> ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUriString())
            .toList();
        
        return ResponseEntity.ok(produtoLinks);
    }

    // Método para buscar produto específico
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable int id) {
        Produto produto = produtos.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        
        return produto != null 
            ? ResponseEntity.ok(produto) 
            : ResponseEntity.notFound().build();
    }
}