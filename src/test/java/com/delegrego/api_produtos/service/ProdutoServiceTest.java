package com.delegrego.api_produtos.service;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.dto.ProdutoResponse;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.mapper.ProdutoMapper;
import com.delegrego.api_produtos.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@Mock
	ProdutoRepository repository;

	@Mock
	ProdutoMapper mapper;

	@InjectMocks
	ProdutoService service;

	@Test
	void deveSalvarERetornarProdutoQuandoDadosForemValidos() {

		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		ProdutoRequest request = new ProdutoRequest("Caneta", "Azul", new BigDecimal("2.00"), "a");

		ProdutoResponse response = new ProdutoResponse(produto.getId(), produto.getNome(), produto.getDescricao(),
				produto.getPreco(), produto.getUrlImagem());

		Mockito.when(mapper.toEntity(request)).thenReturn(produto);

		Mockito.when(repository.save(produto)).thenReturn(produto);

		Mockito.when(mapper.toResponse(produto)).thenReturn(response);

		ProdutoResponse produtoSalvo = service.inserirProduto(request);

		Assertions.assertThat(produtoSalvo).isSameAs(response);
	}

}
