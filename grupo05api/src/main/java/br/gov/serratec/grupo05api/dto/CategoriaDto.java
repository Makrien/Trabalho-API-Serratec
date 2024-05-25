package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Categoria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoriaDto(
        Long id,
        
        @NotNull(message = "Nome não pode ser nulo")
        @NotEmpty(message = "Nome não pode ser vazio")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,
        @NotNull(message = "Descrição não pode ser nula")
        @NotEmpty(message = "Descrição não pode ser vazia")
        @Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
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