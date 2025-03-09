package com.fiap.myapp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

//Acessar http://localhost:8080/
@Controller
public class AsyncController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/obterResultado")
    @ResponseBody
    public DeferredResult<String> obterResultado() {
        DeferredResult<String> resultado = new DeferredResult<>();
        
        // Simulação de operação assíncrona
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                resultado.setResult("resultado pronto");
            } catch (InterruptedException e) {
                resultado.setErrorResult(e);
            }
        }).start();
        
        return resultado;
    }

    @GetMapping("/resultado")
    public String mostrarResultado() {
        return "resultado";
    }
}