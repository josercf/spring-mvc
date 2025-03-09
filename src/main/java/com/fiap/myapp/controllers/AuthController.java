package com.fiap.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    
    // Outros métodos...
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Este método não precisa fazer nada, pois o Spring Security 
        // já irá processar o logout, mas pode ajudar com o roteamento
        return "redirect:/home";
    }
}