package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    
	public List<ClienteDto> buscarTodos() {
		return clienteRepository.findAll().stream().map(c -> new ClienteDto(c.getId(), c.getEmail(),
				c.getNomeCompleto(), c.getCpf(), c.getTelefone(), c.getDataNascimento().toString(), c.getEndereco()))
				.toList();
	}

	public ClienteDto buscarPorId(Long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		if (clienteOptional.isPresent()) {
			Cliente c = clienteOptional.get();
			ClienteDto clienteDto = new ClienteDto(c.getId(), c.getEmail(),
					c.getNomeCompleto(), c.getCpf(),c.getTelefone(), 
					c.getDataNascimento().toString(), c.getEndereco());
			return clienteDto;
		}

		return null;
	}

    public ClienteDto criar(ClienteDto clienteDto) {
    	List<Cliente> listaC = clienteRepository.findAll();
    	for (Cliente cliente : listaC) {
    		if (cliente.getEmail().equals(clienteDto.email())
    		        || cliente.getCpf().equals(clienteDto.cpf())) {
    		    return null;
    		}
    	}
        Cliente cliente = clienteDto.toEntity();
        return cliente.toDto(clienteRepository.save(cliente));
    }

    public ClienteDto atualizar(Long id, ClienteDto clienteDto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteDto.toEntity();
            cliente.setId(id);
            return cliente.toDto(clienteRepository.save(cliente));
        }
        return null;
    }

    public boolean deletar(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
