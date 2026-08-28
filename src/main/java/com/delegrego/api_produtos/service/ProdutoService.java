package com.delegrego.api_produtos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.exception.ProdutoNotFoundException;
import com.delegrego.api_produtos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository repository;

	public Produto inserirProduto(ProdutoRequest produtoDto) {

		Produto produtoEntity = new Produto();
		produtoEntity.setNome(produtoDto.nome());
		produtoEntity.setPreco(produtoDto.preco());
		produtoEntity.setUrlImagem(produtoDto.urlImagem());
		return repository.save(produtoEntity);
	}

	public List<Produto> listarProdutos() {
		return repository.findAll();
	}

	public Produto obterProdutoPorId(int id) {
		return repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
	}

	public Produto atualizarProduto(int id, ProdutoRequest produtoDto) {

		Produto produtoEntity = new Produto();
		produtoEntity.setId(repository.findById(id)
				.orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado")).getId());
		produtoEntity.setNome(produtoDto.nome());
		produtoEntity.setPreco(produtoDto.preco());
		produtoEntity.setUrlImagem(produtoDto.urlImagem());

		return repository.save(produtoEntity);
	}

	public void deletarProduto(int id) {
		repository.deleteById(id);
	}

}
