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
        return clienteRepository.findAll().stream().map(Cliente::toDto).toList();
    }

    public ClienteDto buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(Cliente::toDto).orElse(null);
    }

    public ClienteDto criar(ClienteDto clienteDto) {
        Cliente cliente = clienteDto.toEntity();
        return Cliente.toDto(clienteRepository.save(cliente));
    }

    public ClienteDto atualizar(Long id, ClienteDto clienteDto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteDto.toEntity();
            cliente.setId(id);
            return Cliente.toDto(clienteRepository.save(cliente));
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
