package br.gov.serratec.grupo05api.dto;

import br.gov.serratec.grupo05api.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDto(
		 Long id,
		 @NotBlank(message = "O campo CEP é obrigatório!")
		 String cep,
		 @NotBlank(message = "Informe o nome da rua!")
		 String rua,
		 @NotBlank(message = "Informe o nome do bairro!")
		 String bairro,
		 @NotBlank(message = "Informe o nome da cidade!")
		 String cidade,
		 @NotNull(message = "Informe o número da residência!")
		 int numero,
		 @NotBlank(message = "Informe um complemento!")
		 String complemento,
		 @NotBlank(message = "Informe uma UF!")
		 String uf) {

	public Endereco toEntity() {
		return new Endereco(this.id, this.cep, this.rua, this.bairro,
				this.cidade, this.numero, this.complemento, this.uf);
	}

	
}
