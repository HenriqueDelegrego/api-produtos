package com.delegrego.api_produtos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
	private String descricao;

	@Column(name = "preco", precision = 2, nullable = false)
	private double preco;

	// TODO: Desconto

	@Column(name = "url_imagem", columnDefinition = "TEXT", nullable = false)
	private String urlImagem;

}
