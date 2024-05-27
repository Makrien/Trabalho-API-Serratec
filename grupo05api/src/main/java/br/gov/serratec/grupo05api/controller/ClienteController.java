package br.gov.serratec.grupo05api.controller;


import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.ClienteEnderecoDto;
import br.gov.serratec.grupo05api.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<Page<Cliente>> buscarTodosClientes(@PageableDefault(size = 2, page = 0, sort = "nomeCompleto", 
			direction = Sort.Direction.ASC)Pageable pageable) {
        Page<Cliente> clientes = clienteService.buscarTodos(pageable);
        return ResponseEntity.ok(clientes);
    }
    @Operation(description="Endpoint para buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.buscarPorId(id);
        if (clienteDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDto);
    }
    @Operation(description="Endpoint para cadastrar cliente")
    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody ClienteEnderecoDto clienteDto) {
    	ClienteDto clienteCriado = clienteService.criar(clienteDto);
    	if(clienteCriado == null) {
    		return ResponseEntity.badRequest().body("Email ou cpf do cliente já cadastrados, verifique.");
    	}
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteEnderecoDto clienteDto) {
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
