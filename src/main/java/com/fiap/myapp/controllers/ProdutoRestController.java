package com.fiap.myapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.myapp.models.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {
	List<Produto> produtoList = new ArrayList<>();

	public ProdutoRestController() {
		produtoList.add(new Produto(1, "Produto 1", 100.0));
		produtoList.add(new Produto(2, "Produto 2", 200.0));
		produtoList.add(new Produto(3, "Produto 3", 300.0));
		produtoList.add(new Produto(4, "Produto 4", 400.0));
		produtoList.add(new Produto(5, "Produto 5", 500.0));
	}

	@GetMapping("/")
	public List<Produto> produtos(Model model) {

		return produtoList;
	}

	@GetMapping("/{id}")
	public Produto produto(@PathVariable("id") int id, Model model) {

		Produto produto = produtoList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

		return produto;
	}

	@GetMapping("/produto-valido/{id:[0-9]+}")
	public Produto Produto(@PathVariable("id") int id, Model model) {
		Produto produto = produtoList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

		return produto;
	}
}
