package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Categoria;

public record CategoriaDto(
        Long id,
        String nome,
        String descricao) {

    public Categoria toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Categoria.class);
    }

    public static CategoriaDto toDto(Categoria categoriaEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(categoriaEntity, CategoriaDto.class);
    }
}