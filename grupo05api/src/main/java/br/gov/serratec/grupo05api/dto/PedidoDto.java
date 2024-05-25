package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
  
  public PedidoRelatorioDto toRelatorio() {
		
		List<ItemRelatorioDto> itensRelatorio = new ArrayList<>();
		
		this.itensPedido.forEach(i -> {
			itensRelatorio.add(i.toItemRelatorio());
		});
		  
		return new PedidoRelatorioDto(
				this.id,
				this.dataPedido,
				this.valorTotal,
				itensRelatorio
				);	
	}
}
