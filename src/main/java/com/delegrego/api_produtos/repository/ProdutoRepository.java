package com.delegrego.api_produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.api_produtos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
