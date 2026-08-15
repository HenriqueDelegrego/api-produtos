package com.delegrego.api_produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.api_produtos.entity.Produto;

// O primeiro parâmetro é a entidade (Produto), o segundo é o tipo do ID (Integer)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
