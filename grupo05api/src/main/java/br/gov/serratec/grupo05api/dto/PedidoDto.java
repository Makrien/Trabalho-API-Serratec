package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoDto(Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataEnvio,
        String status,
        Double valorTotal,
        Long idCliente,
        List<Long> idsItemPedido) {

	public Pedido toEntity(Cliente cliente, List<ItemPedido> itens) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(this.dataPedido);
        pedido.setDataEntrega(this.dataEntrega);
        pedido.setDataEnvio(this.dataEnvio);
        pedido.setStatus(this.status);
        pedido.setValorTotal(this.valorTotal);
        pedido.setCliente(cliente);
        pedido.setItensPedido(itens);
        return pedido;
    }

    public static PedidoDto toDto(Pedido pedidoEntity) {
        List<Long> itemIds = pedidoEntity.getItensPedido().stream()
        		.map(ItemPedido::getId)
        		.collect(Collectors.toList());
        
        return new PedidoDto(
        		pedidoEntity.getId(),
        		pedidoEntity.getDataPedido(),
        		pedidoEntity.getDataEntrega(),
        		pedidoEntity.getDataEnvio(),
        		pedidoEntity.getStatus(),
        		pedidoEntity.getValorTotal(),
        		pedidoEntity.getCliente().getId(),
        		itemIds
        );
    }
}
