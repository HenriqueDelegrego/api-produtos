package com.delegrego.api_produtos.exception;

public record FieldError(
		String field,
        String message
) {}