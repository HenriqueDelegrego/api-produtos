package com.delegrego.api_produtos.repository;

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
	void Save_ValidData_ReturnsProduct() {

		// Arrange
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("Azul");
		produto.setPreco(2);
		produto.setUrlImagem("a");

		// Act
		Produto saved = repository.save(produto);

		// Assert
		Assertions.assertThat(saved).isNotNull();
		Assertions.assertThat(saved.getNome()).isEqualTo("Caneta");
	}

}
