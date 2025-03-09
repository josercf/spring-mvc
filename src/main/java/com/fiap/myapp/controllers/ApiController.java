package com.fiap.myapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
 
    @CrossOrigin(origins = "https://dominio-exemplo.com")
	//@CrossOrigin(origins = "http://localhost")
    @GetMapping("/dados")
    public ResponseEntity<String> obterDados() {
        // Lógica para obter dados
        return ResponseEntity.ok("Dados");
    }
}

