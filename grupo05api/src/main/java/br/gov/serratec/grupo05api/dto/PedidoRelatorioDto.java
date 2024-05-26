package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.Cliente;


@JsonIgnoreProperties(ignoreUnknown=true)
public record PedidoRelatorioDto(
		Long idPedido,
		Cliente cliente,
		LocalDate dataPedido,
		Double valorTotal,
		String StatusPedido,
		List<ItemRelatorioDto> itens
		) {
	
	
}