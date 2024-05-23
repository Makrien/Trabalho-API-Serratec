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
		@JsonFormat(pattern = "dd/MM/yyyy")
	    LocalDate dataNascimento,
		Endereco endereco,
		List<Pedido> pedidos) {
	
	
    public Cliente toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Cliente.class);
    }

    public static ClienteDto toDto(Cliente clienteEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(clienteEntity, ClienteDto.class);
    }
}

    
