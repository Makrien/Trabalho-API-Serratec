package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.ItemPedido;

public record ItemPedidoDto(
		Long id,
        int quantidade,
        Double precoVenda,
        Double percentualDesconto,
        Double valorBruto,
        Double valorLiquido,
        Long idProduto,
        Long idPedido) {
	
	// TODO
	public ItemPedido toEntity() {
        
        return null;
    }

	//TODO
    public static ItemPedidoDto toDto(ItemPedido itemPedidoEntity) {
        return null;
    }
}
