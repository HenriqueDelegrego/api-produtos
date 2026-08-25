package com.delegrego.api_produtos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//Define esta classe como um controller REST, ou seja, que responde a requisições HTTP com JSON
@RestController

//Permite requisições de outras origens
@CrossOrigin
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService servico;

	// Create
	// Categoria nos produtos?
	@PostMapping
	public ResponseEntity<Produto> inserirProduto(@Valid @RequestBody ProdutoRequest produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servico.inserirProduto(produto));
	}

	// Read
	// TODO: Paginação
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		return ResponseEntity.status(HttpStatus.OK).body(servico.listarProdutos());
	}

	// Read by id
	// TODO: Get por id com descrição
	@GetMapping("/{id}")
	public ResponseEntity<Produto> obterProdutoPorId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.obterProdutoPorId(id));
	}

	// Update
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable int id, @Valid @RequestBody ProdutoRequest produto) {
		return ResponseEntity.status(HttpStatus.OK).body(servico.atualizarProduto(id, produto));
	}

	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
		servico.deletarProduto(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}
