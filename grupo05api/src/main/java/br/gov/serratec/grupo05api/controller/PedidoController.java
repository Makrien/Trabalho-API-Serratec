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
import jakarta.validation.Valid;

import br.gov.serratec.grupo05api.dto.PedidoDto;
import br.gov.serratec.grupo05api.dto.RelatorioDto;
import br.gov.serratec.grupo05api.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;
	
	//TODO: Check exceptions
	@GetMapping
	public ResponseEntity<List<PedidoDto>> obterTodos() {
		return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
	}
	
	//TODO
//	@GetMapping("/{id}")
//	public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
//		return null;
//	}
	
	//TODO
//	@GetMapping
//	public ResponseEntity<List<RelatorioDto>> obterRelatorio() {
//		return null;
//	}
	
	//TODO
	@PostMapping
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoDto novoPedido) {
		return new ResponseEntity<>(servico.cadastrar(novoPedido), HttpStatus.CREATED);
	}
	
	//TODO
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid PedidoDto pedido) {
		Optional<PedidoDto> pedidoDto = servico.atualizar(id, pedido);
		
		if (pedidoDto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoDto.get());
	}
	
	//TODO
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> excluir(@PathVariable Long id) {
//		return null;
//	}
}