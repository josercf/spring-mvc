package com.fiap.myapp.models;

public class Recurso {
    private Long id;
    private String nome;

    // Construtores
    public Recurso() {}

    public Recurso(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}