package com.delegrego.api_produtos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.api_produtos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByNomeContainingIgnoreCaseAndDescricaoContainingIgnoreCase(String nome, String descricao);

	List<Produto> findByNomeContainingIgnoreCase(String nome);

	List<Produto> findByDescricaoContainingIgnoreCase(String descricao);

}
