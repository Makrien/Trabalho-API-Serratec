package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoDto(Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataEnvio,
        String status,
        Double valorTotal,
        Cliente cliente) {

	public static PedidoDto toDto(Pedido pedidoEntity) {
	    return new PedidoDto(
	        pedidoEntity.getId(),
	        pedidoEntity.getDataPedido(),
	        pedidoEntity.getDataEntrega(),
	        pedidoEntity.getDataEnvio(),
	        pedidoEntity.getStatus(),
	        pedidoEntity.getValorTotal(),
	        pedidoEntity.getCliente()
	    );
	}

}
