
package com.fiap.myapp.controllers;

import com.fiap.myapp.models.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/produto/{id}")
	public String produto(@PathVariable("id") int id, Model model) {

		Produto produto = produtoList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

		model.addAttribute("product", produto);
		return "produto";
	}

	@GetMapping("/produto-valido/{id:[0-9]+}")
	public String Produto(@PathVariable("id") int id, Model model) {
		Produto produto = produtoList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

		model.addAttribute("product", produto);
		return "produto";
	}
}
