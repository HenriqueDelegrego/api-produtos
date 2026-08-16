package com.delegrego.api_produtos.exception;

public class ProdutoNotFoundException extends RuntimeException {

	public ProdutoNotFoundException(String message) {
		super(message);
	}

}
