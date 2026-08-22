package com.delegrego.api_produtos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.exception.ProdutoNotFoundException;
import com.delegrego.api_produtos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

//Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository repository;

	// Create
	public Produto inserirProduto(ProdutoRequest produtoDto) {

		Produto produtoEntity = new Produto();
		produtoEntity.setNome(produtoDto.nome());
		produtoEntity.setPreco(produtoDto.preco());
		produtoEntity.setUrlImagem(produtoDto.urlImagem());

		// Insere um produto no banco de dados
		return repository.save(produtoEntity);
	}

	// Read
	public List<Produto> listarProdutos() {
		// Lista todos os produtos
		return repository.findAll();
	}

	public Produto obterProdutoPorId(int id) {
		return repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
	}

	// Update
	public Produto atualizarProduto(int id, ProdutoRequest produtoDto) {

		Produto produtoEntity = new Produto();
		produtoEntity.setId(repository.findById(id)
				.orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado")).getId());
		produtoEntity.setNome(produtoDto.nome());
		produtoEntity.setPreco(produtoDto.preco());
		produtoEntity.setUrlImagem(produtoDto.urlImagem());

		// Atualiza um produto
		return repository.save(produtoEntity);
	}

	// Delete
	public void deletarProduto(int id) {
		// Deleta um produto a partir do id
		repository.deleteById(id);
	}

}
