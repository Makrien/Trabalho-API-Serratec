package br.gov.serratec.grupo05api.dto;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Categoria;

public record CategoriaDto(
        Long id,
        String nome,
        String descricao,
        List<ProdutoDto> produto) {

    public Categoria toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Categoria.class);
    }

    public static CategoriaDto toDto(Categoria categoriaEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(categoriaEntity, CategoriaDto.class);
    }
}