package br.gov.serratec.grupo05api.controller;

import java.util.List;
import java.util.Optional;

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

import br.gov.serratec.grupo05api.dto.EnderecoDto;
import br.gov.serratec.grupo05api.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	
	@Autowired
	private EnderecoService servico;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDto>> obterTodos() {
		return ResponseEntity.ok(servico.obterTodos());
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody @Valid EnderecoDto endereco) {
		return ResponseEntity.ok(servico.cadastrarEndereco(endereco));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Optional<EnderecoDto>> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoDto enderecoAtualizado) {
		return ResponseEntity.ok(servico.atualizarEndereco(id, enderecoAtualizado));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
		if (servico.excluiEndereco(id)) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <EnderecoDto> obterEnderecoPorId(@PathVariable Long id) {
		Optional<EnderecoDto> endereco = servico.obterEnderecoPorId(id);
		
		if (endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
