package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.dto.ClienteEnderecoDto;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Endereco;
import br.gov.serratec.grupo05api.repository.ClienteRepository;
import br.gov.serratec.grupo05api.repository.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
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

    public ClienteDto criar(ClienteEnderecoDto clienteDto) {
    	List<Cliente> listaC = clienteRepository.findAll();
    	for (Cliente cliente : listaC) {
    		if (cliente.getEmail().equals(clienteDto.email())
    		        || cliente.getCpf().equals(clienteDto.cpf())) {
    		    return null;
    		}
    	}
        Cliente cliente = clienteDto.toEntity();
        Optional<Endereco> clienteEndereco = enderecoRepository.findById(clienteDto.enderecoId());
        cliente.setEndereco(clienteEndereco.get());
        clienteRepository.save(cliente);
        return Cliente.toDto(cliente);
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
