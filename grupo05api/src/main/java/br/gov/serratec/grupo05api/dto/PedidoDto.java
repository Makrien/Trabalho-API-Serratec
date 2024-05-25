package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.gov.serratec.grupo05api.config.Mapper;
import br.gov.serratec.grupo05api.model.Cliente;

import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoDto(Long id, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio, String status,
		Double valorTotal, Cliente cliente, List<ItemPedidoDto> itensPedido) {

	public Pedido toEntity() {
        return Mapper.getMapper().convertValue(this, Pedido.class);
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

	public static PedidoDto toDto(Pedido pedidoEntity) {
		return Mapper.getMapper().convertValue(pedidoEntity, PedidoDto.class);
	}
}
