package br.gov.serratec.grupo05api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Endereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDto(
		 Long id,
		 String cep,
		 @JsonAlias("logradouro") String rua,
		 String bairro,
		 @JsonAlias("localidade") String cidade,
		 int numero,
		 String complemento,
		 String uf,
		 Cliente cliente
		 ) {

	public Endereco toEntity() {
		return new Endereco(this.id, this.cep, this.rua, this.bairro,
				this.cidade, this.numero, this.complemento, this.uf);
	}

}