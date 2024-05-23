package br.gov.serratec.grupo05api.controller;

import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.repository.ClienteRepository;
import br.gov.serratec.grupo05api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    private ClienteRepository clienteRepository;

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
    public ResponseEntity<?> criarCliente(@RequestBody ClienteDto clienteDto) {
    	ClienteDto clienteCriado = clienteService.criar(clienteDto);
    	if(clienteCriado == null) {
    		return ResponseEntity.badRequest().body("Email ou cpf do cliente já cadastrados, verifique.");
    	}
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
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
