package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import br.gov.serratec.grupo05api.model.Produto;

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
        return new ProdutoDto(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getQtdEstoque(),
                produtoEntity.getDataCadastro(),
                produtoEntity.getValorUnitario(),
                produtoEntity.getImagem(),
                CategoriaDto.toDto(produtoEntity.getCategoria())
        );
    }

}
