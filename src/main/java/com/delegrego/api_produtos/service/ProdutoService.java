package com.delegrego.api_produtos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

//Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
@RequiredArgsConstructor
public class ProdutoService {

	// Injeta automaticamente a interface de repositório que acessa o banco de dados

	private final ProdutoRepository repo;

	// Create
	public Produto inserirProduto(ProdutoRequest p) {

		Produto produto = new Produto();
		produto.setNome(p.nome());
		produto.setPreco(p.preco());

		// Insere um produto no banco de dados
		return repo.save(produto);
	}

	// Read
	public List<Produto> listarProdutos() {
		// Lista todos os produtos
		return repo.findAll();
	}

	public Produto obterProdutoPorId(int id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	// Update
	public Produto atualizarProduto(int id, ProdutoRequest p) {

		Produto produto = new Produto();
		produto.setId(repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado")).getId());
		produto.setNome(p.nome());
		produto.setPreco(p.preco());

		// Atualiza um produto
		return repo.save(produto);
	}

	// Delete
	public void deletarProduto(int id) {
		// Deleta um produto a partir do id
		repo.deleteById(id);
	}

}
