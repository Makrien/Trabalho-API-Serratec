package br.gov.serratec.grupo05api.dto;

import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.model.Produto;

public record ItemPedidoCadastroDto(
			Long id,
			int quantidade,
			Double precoVenda,
			Double percentualDesconto,
			Double valorBruto,
			Double valorLiquido,
			Long idProduto,
			Long idPedido
		) {

	public ItemPedido toEntity(Produto produto, Pedido pedido) {
		ItemPedido item = new ItemPedido();
		item.setId(this.id);
		item.setQuantidade(this.quantidade);
		item.setPrecoVenda(this.precoVenda);
		item.setPercentualDesconto(this.percentualDesconto);
		item.setValorBruto(this.valorBruto);
		item.setValorLiquido(this.valorLiquido);
		item.setProduto(produto);
		item.setPedido(pedido);
		return item;
	}
}
