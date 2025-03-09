package com.fiap.myapp.controllers;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;


//acessar http://localhost:8080/processarAssincrono

@Controller
public class ViewController {

	    @GetMapping("/home")
	    public String home() {
	        return "home"; // Thymeleaf template
	    }
	    
	    @RequestMapping("/processarAssincrono")
	    public Callable processarAssincrono() {
	        return () -> {

	            // Simulação de processamento demorado
	            Thread.sleep(2000);

	            return ResponseEntity.ok("Processamento concluído com sucesso!");
	        };
	    }
}

