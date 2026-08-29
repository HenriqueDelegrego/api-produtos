package com.delegrego.api_produtos.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProdutoCreateRequest(

		@NotBlank(message = "Nome é obrigatório")
		@Size(max = 50, message = "Nome não pode ultrapassar 50 caracteres")
		String nome,

		@NotBlank(message = "Descrição é obrigatória")
		String descricao,

		@PositiveOrZero(message = "Preço não pode ser negativo")
		@Digits(integer = 10, fraction = 2, message = "Preço não válido")
		double preco,

		@NotBlank(message = "Url da imagem é obrigatória")
		String urlImagem

) {}
