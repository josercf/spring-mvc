package com.fiap.myapp.controllers;
import com.fiap.myapp.models.Produto;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class ProdutoContoller {
    List<Produto> produtoList = new ArrayList<>();
    
    public ProdutoContoller() {
        produtoList.add(new Produto(1, "Produto 1", 100.0));
        produtoList.add(new Produto(2, "Produto 2", 200.0));
        produtoList.add(new Produto(3, "Produto 3", 300.0));
        produtoList.add(new Produto(4, "Produto 4", 400.0));
        produtoList.add(new Produto(5, "Produto 5", 500.0));
    }
    
    @GetMapping("/produtos")
    public String produtos(Model model) {
        model.addAttribute("products", produtoList);
        return "produtos";
    }
    
    // Implementando uma versão REST que retorna JSON com cache HTTP
    @GetMapping("/api/produtos")
    public ResponseEntity<List<Produto>> getProdutos() {
        // Definindo o controle de cache: 30 segundos
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS)
                .noTransform()
                .mustRevalidate();
        
        // Criando um hash simples para ETag
        String etag = String.valueOf(produtoList.hashCode());
        
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .eTag(etag)
                .body(produtoList);
    }
    
    @GetMapping("/produto/{id}")
    public String produto(@PathVariable("id") int id, Model model) {
        if (id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        Produto produto = produtoList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", produto);
        return "produto";
    }
    
    // Implementando uma versão REST que retorna JSON com cache HTTP
    @GetMapping("/api/produto/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") int id, 
                                             @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        if (id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        
        Produto produto = produtoList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Criando um hash simples para ETag baseado no produto e última modificação
        String etag = String.valueOf(produto.hashCode());
        
        // Verificando se o cliente já tem a versão mais recente
        if (ifNoneMatch != null && ifNoneMatch.equals(etag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .eTag(etag)
                    .build();
        }
        
        // Define o controle de cache: 60 segundos
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS)
                .noTransform()
                .mustRevalidate();
        
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .eTag(etag)
                .body(produto);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/produto-valido/{id:[0-9]+}")
    public String Produto(@PathVariable("id") int id, Model model) {
        Produto produto = produtoList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido!");
        }
        
        model.addAttribute("product", produto);
        return "produto";
    }
}