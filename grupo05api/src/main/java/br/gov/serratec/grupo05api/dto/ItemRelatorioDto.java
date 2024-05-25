package br.gov.serratec.grupo05api.dto;

public record ItemRelatorioDto(Long codigo,
		String nomeProduto,
		Double precoVenda,
		int quantidade,
		Double valorBruto,
		Double percentualDesconto,
		Double valorLiquido) {

}
