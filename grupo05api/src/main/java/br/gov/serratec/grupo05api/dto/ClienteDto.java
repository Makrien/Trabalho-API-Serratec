package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Endereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ClienteDto(
			Long id,
			String email,
			String nomeCompleto,
			String cpf,
			String telefone,
		    String dataNascimento,
		    Endereco endereco) {
		
		
	    public Cliente toEntity() {
	        Cliente cliente = new Cliente();
	        cliente.setEmail(this.email);
	        cliente.setNomeCompleto(this.nomeCompleto);
	        cliente.setCpf(this.cpf);
	        cliente.setTelefone(this.telefone);
	        cliente.setDataNascimento(LocalDate.parse(dataNascimento));
	        cliente.setEndereco(this.endereco);
	        return cliente;
	    }

}
