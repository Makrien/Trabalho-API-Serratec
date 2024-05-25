package br.gov.serratec.grupo05api.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Autowired
    private EmailService emailService;


    public Page<Cliente> buscarTodos(Pageable pageable)  {
    		Page<Cliente> c =  clienteRepository.findAll(pageable);
            return c;       
        }

    public ClienteDto buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente c = clienteOptional.get();
            return new ClienteDto(c.getId(), c.getEmail(), c.getNomeCompleto(), c.getCpf(), c.getTelefone(), c.getDataNascimento(), c.getEndereco());
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
        emailService.enviarEmail(cliente.getEmail(), "Cadastro de cliente.", "Olá " + cliente.getNomeCompleto() + ", Seu cadastro foi efetuado com sucesso!!");
        return Cliente.toDto(cliente);
    }

    public ClienteDto atualizar(Long id, ClienteEnderecoDto c) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Optional<Endereco> eOptional = enderecoRepository.findById(c.enderecoId());
        if (clienteOptional.isPresent()) {
            ClienteDto clienteDto = new ClienteDto(id,c.email(),c.nomeCompleto(),
            		c.cpf(),c.telefone(), c.dataNascimento(),eOptional.get());
            return Cliente.toDto(clienteRepository.save(clienteDto.toEntity()));
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
