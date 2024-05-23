package br.gov.serratec.grupo05api.dto;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Endereco;
import br.gov.serratec.grupo05api.model.Pedido;

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
    public ClienteDto toDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNomeCompleto(),
				cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento().toString(), cliente.getEndereco());
	}

   
}

    
