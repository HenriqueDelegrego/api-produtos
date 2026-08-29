package com.delegrego.api_produtos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delegrego.api_produtos.dto.ProdutoListResponse;
import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.dto.ProdutoResponse;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.exception.ProdutoNotFoundException;
import com.delegrego.api_produtos.mapper.ProdutoMapper;
import com.delegrego.api_produtos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository repository;
	private final ProdutoMapper mapper;

	public ProdutoResponse inserirProduto(ProdutoRequest produtoDto) {
		return mapper.toResponse(repository.save(mapper.toEntity(produtoDto)));
	}

	public List<ProdutoListResponse> listarProdutos(String nome) {
		return nome == null || nome.isBlank() ? mapper.toListResponse(repository.findAll())
				: mapper.toListResponse(repository.findByNomeContainingIgnoreCase(nome.strip()));
	}

	public ProdutoResponse obterProdutoPorId(int id) {
		return mapper.toResponse(
				repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado")));
	}

	public ProdutoResponse atualizarProduto(int id, ProdutoRequest produtoDto) {

		Produto produtoEntity = repository.findById(id)
				.orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

		mapper.updateEntityFromDto(produtoDto, produtoEntity);

		return mapper.toResponse(repository.save(produtoEntity));
	}

	public void deletarProduto(int id) {
		repository.deleteById(id);
	}

}
