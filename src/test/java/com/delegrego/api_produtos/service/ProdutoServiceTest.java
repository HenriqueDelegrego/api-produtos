package com.delegrego.api_produtos.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.entity.Produto;
import com.delegrego.api_produtos.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@Mock
	ProdutoRepository repository;

	@InjectMocks
	ProdutoService service;

	@Test
	public void create_savesAndReturnsUser() {
		ProdutoRequest request = new ProdutoRequest("Caneta", "Azul", 2, "a");

	//	Produto saved = new Produto(1, "Caneta", "Azul", 2, "a");
		
		// when(repository.save(request).then)
	}

}
