package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.model.Produto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemPedidoDto(
		Long id,
        int quantidade,
        Double precoVenda,
        Double percentualDesconto,
        Double valorBruto,
        Double valorLiquido,
        Produto produto,
        Pedido pedido) {

    public static ItemPedidoDto toDto(ItemPedido itemPedidoEntity) {
        return new ItemPedidoDto(
        		itemPedidoEntity.getId(),
        		itemPedidoEntity.getQuantidade(),
        		itemPedidoEntity.getPrecoVenda(),
        		itemPedidoEntity.getPercentualDesconto(),
        		itemPedidoEntity.getValorBruto(),
        		itemPedidoEntity.getValorLiquido(),
        		itemPedidoEntity.getProduto(),
        		itemPedidoEntity.getPedido()
        );
    }
    
    public ItemRelatorioDto toItemRelatorio() {
		  return new ItemRelatorioDto(
				  this.id,
				  this.produto.getNome(),
				  this.precoVenda,
				  this.quantidade,
				  this.valorBruto,
				  this.percentualDesconto,
				  this.valorLiquido
				  );
	  }
}
