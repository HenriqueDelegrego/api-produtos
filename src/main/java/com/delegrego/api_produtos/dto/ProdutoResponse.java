package com.delegrego.api_produtos.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
		
		int id,

		String nome,
		
		String descricao,

		BigDecimal preco,

		String urlImagem
		
) {}
