package br.gov.serratec.grupo05api.controller;

import java.util.List;

import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.repository.ClienteRepository;
import br.gov.serratec.grupo05api.service.ClienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.dto.ClienteEnderecoDto;
import br.gov.serratec.grupo05api.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> buscarTodosClientes() {
        List<ClienteDto> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.buscarPorId(id);
        if (clienteDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDto);
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody ClienteDto clienteDto) {
    	ClienteDto clienteCriado = clienteService.criar(clienteDto);
    	if(clienteCriado == null) {
    		return ResponseEntity.badRequest().body("Email ou cpf do cliente já cadastrados, verifique.");
    	}
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteAtualizado = clienteService.atualizar(id, clienteDto);
        if (clienteAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        boolean deletado = clienteService.deletar(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
