package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return clienteRepository.findAll().stream()
                .map(c -> new ClienteDto(c.getId(), c.getEmail(), c.getNomeCompleto(), c.getCpf(), c.getTelefone(), c.getDataNascimento().toString(), c.getEndereco()))
                .toList();
    }

    public ClienteDto buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente c = clienteOptional.get();
            return new ClienteDto(c.getId(), c.getEmail(), c.getNomeCompleto(), c.getCpf(), c.getTelefone(), c.getDataNascimento().toString(), c.getEndereco());
        }
        return null;
    }

    public ClienteDto criar(ClienteEnderecoDto clienteDto) {
        if (clienteRepository.existsByEmail(clienteDto.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado.");
        }
        if (clienteRepository.existsByCpf(clienteDto.cpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado.");
        }

        Cliente cliente = clienteDto.toEntity();
        Optional<Endereco> clienteEndereco = enderecoRepository.findById(clienteDto.enderecoId());
        if (clienteEndereco.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço não encontrado.");
        }
        cliente.setEndereco(clienteEndereco.get());
        clienteRepository.save(cliente);
        return Cliente.toDto(cliente);
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
