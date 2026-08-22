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

// Indica que esta classe é uma entidade JPA
@Entity

// Define o nome da tabela no banco de dados que esta entidade representa
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

	// Define o campo 'id' como a chave primária da tabela
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Mapeia o campo 'id' para a coluna 'id' no banco de dados
	@Column(name = "id")
	private int id;

	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "preco", precision = 2, nullable = false)
	private double preco;

	@Column(name = "url_imagem", nullable = false)
	private String urlImagem;

}
