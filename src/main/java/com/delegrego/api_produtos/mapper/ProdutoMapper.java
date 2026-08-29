package com.delegrego.api_produtos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.delegrego.api_produtos.dto.ProdutoListResponse;
import com.delegrego.api_produtos.dto.ProdutoRequest;
import com.delegrego.api_produtos.dto.ProdutoResponse;
import com.delegrego.api_produtos.entity.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

	Produto toEntity(ProdutoRequest request);

	List<ProdutoListResponse> toListResponse(List<Produto> listaProdutos);

	ProdutoResponse toResponse(Produto entity);

	void updateEntityFromDto(ProdutoRequest request, @MappingTarget Produto entity);

}
