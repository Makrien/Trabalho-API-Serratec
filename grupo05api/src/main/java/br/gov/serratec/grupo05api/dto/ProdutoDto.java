package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.config.Mapper;
import br.gov.serratec.grupo05api.model.Produto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProdutoDto(
		Long id,
        String nome,
        String descricao,
        Long qtdEstoque,
        LocalDate dataCadastro,
        Double valorUnitario,
        String imagem,
        CategoriaDto categoria) {

	public static ProdutoDto toDto(Produto produtoEntity) {
		return Mapper.getMapper().convertValue(produtoEntity, ProdutoDto.class);
    }

}
