package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.model.Produto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemPedidoCadastroDto(
			Long id,
			int quantidade,
			Double percentualDesconto,
			Long idProduto,
			Long idPedido
		) {

	public ItemPedido toEntity(Produto produto, Pedido pedido) {
		ItemPedido item = new ItemPedido();
		item.setId(this.id);
		item.setQuantidade(this.quantidade);
		item.setPercentualDesconto(this.percentualDesconto);
		item.setProduto(produto);
		item.setPedido(pedido);
		return item;
	}
}
