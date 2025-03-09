package com.fiap.myapp.service;


import org.springframework.stereotype.Service;

import com.fiap.myapp.models.Recurso;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursoService {
    private List<Recurso> recursos = new ArrayList<>();

    public RecursoService() {
        // Inicializa com alguns recursos de exemplo
        recursos.add(new Recurso(1L, "Recurso 1"));
        recursos.add(new Recurso(2L, "Recurso 2"));
        recursos.add(new Recurso(3L, "Recurso 3"));
    }

    public Recurso buscarPorId(Long id) {
        return recursos.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}