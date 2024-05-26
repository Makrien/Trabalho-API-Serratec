package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public record PedidoRelatorioDto(
		Long idPedido,
		LocalDate dataPedido,
		Double valorTotal,
		List<ItemRelatorioDto> itens
		) {
	
	
}