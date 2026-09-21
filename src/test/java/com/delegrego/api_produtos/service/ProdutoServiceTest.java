package com.delegrego.api_produtos.service;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delegrego.api_produtos.dto.ProdutoListResponse;
import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.dto.ProdutoResponse;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.mapper.ProdutoMapper;
import com.delegrego.api_produtos.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@Mock
	private ProdutoRepository repository;

	@Mock
	private ProdutoMapper mapper;

	@InjectMocks
	private ProdutoService service;

	@Test
	void deveSalvarERetornarProdutoQuandoDadosForemValidos() {

		// Arrange
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

		// Act
		ProdutoResponse produtoSalvo = service.inserirProduto(request);

		// Assert
		Assertions.assertThat(produtoSalvo).isEqualTo(response);
	}

	@Test
	void deveListarProdutosQuandoExistemProdutos() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		Produto produto2 = new Produto();
		produto2.setNome("Lápis");
		produto2.setDescricao("Azul");
		produto2.setPreco(new BigDecimal("2.00"));
		produto2.setUrlImagem("a");

		ProdutoListResponse response1 = new ProdutoListResponse(produto.getId(), produto.getNome(), produto.getPreco(),
				produto.getUrlImagem());

		ProdutoListResponse response2 = new ProdutoListResponse(produto2.getId(), produto2.getNome(),
				produto2.getPreco(), produto2.getUrlImagem());

		Mockito.when(repository.findAll()).thenReturn(List.of(produto, produto2));

		Mockito.when(mapper.toListResponse(List.of(produto, produto2))).thenReturn(List.of(response1, response2));

		// Act
		List<ProdutoListResponse> listaProdutos = service.listarProdutos(null, null);

		// Assert
		Assertions.assertThat(listaProdutos).containsExactlyInAnyOrder(response1, response2);
	}

	@Test
	void deveListarProdutosQuandoPesquisaParcialPorNomeForValida() {

		// Arrange
		Produto produto1 = new Produto();
		produto1.setNome("Caneta");
		produto1.setDescricao("Azul");
		produto1.setPreco(new BigDecimal("2.00"));
		produto1.setUrlImagem("a");

		Produto produto2 = new Produto();
		produto2.setNome("Canetão");
		produto2.setDescricao("Azul");
		produto2.setPreco(new BigDecimal("1.50"));
		produto2.setUrlImagem("b");

		ProdutoListResponse response1 = new ProdutoListResponse(produto1.getId(), produto1.getNome(),
				produto1.getPreco(), produto1.getUrlImagem());

		ProdutoListResponse response2 = new ProdutoListResponse(produto2.getId(), produto2.getNome(),
				produto2.getPreco(), produto2.getUrlImagem());

		Mockito.when(repository.findByNomeContainingIgnoreCase("Canet")).thenReturn(List.of(produto1, produto2));

		Mockito.when(mapper.toListResponse(List.of(produto1, produto2))).thenReturn(List.of(response1, response2));

		// Act
		List<ProdutoListResponse> listaProdutos = service.listarProdutos("Canet", null);

		// Assert
		Assertions.assertThat(listaProdutos).containsExactlyInAnyOrder(response1, response2);
	}

}
