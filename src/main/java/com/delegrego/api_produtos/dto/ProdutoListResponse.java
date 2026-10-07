package com.delegrego.api_produtos.dto;

import java.math.BigDecimal;

public record ProdutoListResponse(

		Long id,

		String nome,

		BigDecimal preco,

		String urlImagem

) {}
