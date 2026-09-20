package com.delegrego.api_produtos.dto;

import java.math.BigDecimal;

public record ProdutoListResponse(

		int id,

		String nome,

		BigDecimal preco,

		String urlImagem

) {}
