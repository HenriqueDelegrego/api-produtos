package com.delegrego.api_produtos.dto;

public record ProdutoListResponse(

		int id,

		String nome,

		double preco,

		String urlImagem

) {}
