package com.delegrego.api_produtos.dto;

public record ProdutoResponse(
		
		int id,

		String nome,
		
		String descricao,

		double preco,

		String urlImagem
		
) {}
