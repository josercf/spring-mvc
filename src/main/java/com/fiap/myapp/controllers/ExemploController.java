package com.fiap.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.myapp.exceptions.RecursoNaoEncontradoException;
import com.fiap.myapp.models.Recurso;
import com.fiap.myapp.service.RecursoService;


//URL teste: http://localhost:8080/api/recurso/1
//URL teste: http://localhost:8080/api/recurso/9
@RestController
@RequestMapping("/api")
public class ExemploController {

	@Autowired
	private RecursoService recursoService;

	@GetMapping("/recurso/{id}")
	public ResponseEntity<Recurso> obterRecurso(@PathVariable Long id) {
		Recurso recurso = recursoService.buscarPorId(id);
		if (recurso == null) {
			throw new RecursoNaoEncontradoException("Recurso não encontrado com o ID: " + id);
		}
		return ResponseEntity.ok(recurso);
	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ProblemDetail> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetail.setTitle("Recurso Não Encontrado");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}
}
