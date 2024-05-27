package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Pedido;

@JsonIgnoreProperties(ignoreUnknown = true)
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
  
  public PedidoRelatorioDto toRelatorio(List<ItemPedidoDto> itensPedido) {
		
		List<ItemRelatorioDto> itensRelatorio = new ArrayList<>();
				
		itensPedido.forEach(i -> {
			itensRelatorio.add(i.toItemRelatorio());
		});
				  
		return new PedidoRelatorioDto(
				this.id,
				this.cliente,
				this.dataPedido,
				this.valorTotal,
				this.status,
				itensRelatorio
				);	
	}

}
