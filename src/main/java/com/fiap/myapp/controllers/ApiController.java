package com.fiap.myapp.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/publico")
    public String endpointPublico() {
        return "Endpoint público - Acessível para todos";
    }

    @GetMapping("/usuario")
    @PreAuthorize("hasRole('USER')")
    public String endpointUsuario() {
        return "Endpoint de usuário - Requer autenticação como USER";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String endpointAdmin() {
        return "Endpoint administrativo - Requer autenticação como ADMIN";
    }
}

