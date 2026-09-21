package com.delegrego.api_produtos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.delegrego.api_produtos.entity.Produto;

@DataJpaTest
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repository;

	@Test
	void deveSalvarProdutoQuandoDadosForemValidos() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		// Act
		Produto salvo = repository.save(produto);

		// Assert
		Assertions.assertThat(salvo).isNotNull();
		Assertions.assertThat(salvo.getId()).isGreaterThan(0);
		Assertions.assertThat(salvo.getNome()).isEqualTo("Caneta");
		Assertions.assertThat(salvo.getPreco()).isEqualByComparingTo(new BigDecimal("2.00"));
	}

	@Test
	void deveRetornarTodosOsProdutosQuandoExistiremProdutosCadastrados() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		Produto produto2 = new Produto();
		produto2.setNome("Lápis");
		produto2.setDescricao("Azul");
		produto2.setPreco(new BigDecimal("1.50"));
		produto2.setUrlImagem("b");

		repository.save(produto);
		repository.save(produto2);

		// Act
		List<Produto> listaProdutos = repository.findAll();

		// Assert
		Assertions.assertThat(listaProdutos).containsExactlyInAnyOrder(produto, produto2);
	}

	@Test
	void deveRetornarProdutosQuandoNomeForEncontrado() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		Produto produto2 = new Produto();
		produto2.setNome("Lápis");
		produto2.setDescricao("Azul");
		produto2.setPreco(new BigDecimal("1.50"));
		produto2.setUrlImagem("b");

		Produto produto3 = new Produto();
		produto3.setNome("Canetão");
		produto3.setDescricao("Azul");
		produto3.setPreco(new BigDecimal("1.50"));
		produto3.setUrlImagem("b");

		repository.save(produto);
		repository.save(produto2);
		repository.save(produto3);

		// Act
		List<Produto> produtosRetornados = repository.findByNomeContainingIgnoreCase("Canet");

		// Assert
		Assertions.assertThat(produtosRetornados).containsExactlyInAnyOrder(produto, produto3);
	}

	@Test
	void deveRetornarListaVaziaQuandoNomeNaoForEncontrado() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(new BigDecimal("2.00"));
		produto.setUrlImagem("a");

		Produto produto2 = new Produto();
		produto2.setNome("Lápis");
		produto2.setDescricao("Azul");
		produto2.setPreco(new BigDecimal("1.50"));
		produto2.setUrlImagem("b");

		Produto produto3 = new Produto();
		produto3.setNome("Canetão");
		produto3.setDescricao("Azul");
		produto3.setPreco(new BigDecimal("1.50"));
		produto3.setUrlImagem("b");

		repository.save(produto);
		repository.save(produto2);
		repository.save(produto3);

		// Act
		List<Produto> listaVazia = repository.findByNomeContainingIgnoreCase("Grampeador");

		// Assert
		Assertions.assertThat(listaVazia).isEmpty();
	}
}