package br.gov.serratec.grupo05api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.PedidoCadastroDto;
import br.gov.serratec.grupo05api.dto.PedidoDto;
//import br.gov.serratec.grupo05api.dto.RelatorioDto;
import br.gov.serratec.grupo05api.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<PedidoDto>> obterTodos() {
		return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
		Optional<PedidoDto> pedidoDto = servico.obterPorId(id);
		if (pedidoDto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pedidoDto.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoCadastroDto novoPedido) {
		return new ResponseEntity<>(servico.cadastrar(novoPedido), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid PedidoCadastroDto pedido) {
		Optional<PedidoDto> pedidoDto = servico.atualizar(id, pedido);
		
		if (pedidoDto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoDto.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if (servico.excluir(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}