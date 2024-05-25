package br.gov.serratec.grupo05api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.gov.serratec.grupo05api.dto.ItemPedidoCadastroDto;
import br.gov.serratec.grupo05api.dto.ItemPedidoDto;
import br.gov.serratec.grupo05api.service.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDto>> listarTodos() {
        List<ItemPedidoDto> itensPedido = itemPedidoService.listarTodos();
        return ResponseEntity.ok(itensPedido);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> buscarPorId(@PathVariable Long id) {
        Optional<ItemPedidoDto> itemPedidoDto = itemPedidoService.buscarPorId(id);
        return itemPedidoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ItemPedidoDto> cadastrar(@RequestBody ItemPedidoCadastroDto novoItemPedido) {
        ItemPedidoDto itemPedidoDto = itemPedidoService.cadastrar(novoItemPedido);
        return ResponseEntity.status(201).body(itemPedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> atualizar(@PathVariable Long id, @RequestBody ItemPedidoCadastroDto itemPedidoDto) {
        Optional<ItemPedidoDto> atualizado = itemPedidoService.atualizar(id, itemPedidoDto);
        return atualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
