package br.gov.serratec.grupo05api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoCadastroDto(
		@NotBlank(message = "O campo CEP é obrigatório!")
		@Size(min = 8, max = 8)
		String cep,
		int numero,
		String complemento
	) {}
