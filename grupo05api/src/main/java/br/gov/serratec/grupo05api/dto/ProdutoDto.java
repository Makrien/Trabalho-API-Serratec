package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.gov.serratec.grupo05api.model.Produto;

public record ProdutoDto(
		Long id,
        String nome,
        String descricao,
        Long qtdEstoque,
        String dataCadastro,
        Double valorUnitario,
        String imagem,
        CategoriaDto categoria,
        List<ItemPedidoDto> itemPedido) {
	
	public Produto toEntity() {
		Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQtdEstoque(qtdEstoque);
        produto.setDataCadastro(LocalDate.parse(this.dataCadastro));
        produto.setValorUnitario(this.valorUnitario);
        produto.setImagem(this.imagem);
        produto.setCategoria(categoria.toEntity());
        
        return produto;
        
    }

	public static ProdutoDto toDto(Produto produtoEntity) {
        return new ProdutoDto(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getQtdEstoque(),
                produtoEntity.getDataCadastro().toString(),
                produtoEntity.getValorUnitario(),
                produtoEntity.getImagem(),
                CategoriaDto.toDto(produtoEntity.getCategoria()),
                produtoEntity.getItemPedido().stream()
                        .map(ItemPedidoDto::toDto)
                        .collect(Collectors.toList())
        );
    }

}
