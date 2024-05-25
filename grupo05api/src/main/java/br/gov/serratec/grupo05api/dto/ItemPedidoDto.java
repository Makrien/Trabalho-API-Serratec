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
        ProdutoDto produto,
        PedidoDto pedido) {
	public ItemPedido toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, ItemPedido.class);
    }

    public static ItemPedidoDto toDto(ItemPedido itemPedidoEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(itemPedidoEntity, ItemPedidoDto.class);
    }
    
    public ItemRelatorioDto toItemRelatorio() {
		  return new ItemRelatorioDto(
				  this.id,
				  this.produto.nome(),
				  this.precoVenda,
				  this.quantidade,
				  this.valorBruto,
				  this.percentualDesconto,
				  this.valorLiquido
				  );
	  }
}
