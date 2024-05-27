package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.config.Mapper;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Pedido;

@JsonIgnoreProperties(ignoreUnknown=true)
public record PedidoRelatorioDto(
		Long idPedido,
		Cliente cliente,
		LocalDate dataPedido,
		Double valorTotal,
		String StatusPedido,
		List<ItemRelatorioDto> itens
		) {
	
	public Pedido toEntity() {
		return Mapper.getMapper().convertValue(this, Pedido.class);
	}
	
	public static PedidoRelatorioDto toDto(Pedido pedidoEntity) {
		return Mapper.getMapper().convertValue(pedidoEntity, PedidoRelatorioDto.class);
	}
	
}