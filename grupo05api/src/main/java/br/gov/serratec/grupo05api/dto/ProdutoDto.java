package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Produto;

public record ProdutoDto(
		Long id,
        String nome,
        String descricao,
        Long qtdEstoque,
        LocalDate dataCadastro,
        Double valorUnitario,
        byte[] imagem,
        CategoriaDto categoria,
        List<ItemPedidoDto> itemPedido) {
	
	public Produto toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Produto.class);
    }

    public static ProdutoDto toDto(Produto produtoEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(produtoEntity, ProdutoDto.class);
    }

}
