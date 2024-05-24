package br.gov.serratec.grupo05api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.CategoriaDto;
import br.gov.serratec.grupo05api.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
    private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> obterTodos() {
		List<CategoriaDto> categorias = categoriaService.obterTodos();
        return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> obterPorId(@PathVariable Long id) {
        CategoriaDto categoria = categoriaService.obterPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoria);
        }
    }
	
	@PostMapping
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto novaCategoria = categoriaService.cadastrarCategoria(categoriaDto);
        if (novaCategoria == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok(novaCategoria);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDto);
        if (categoriaAtualizada == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoriaAtualizada);
        }
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        Boolean deletada = categoriaService.deletarCategoria(id);
        if (deletada) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/buscar")
    public ResponseEntity<List<CategoriaDto>> buscarPorNome(@RequestParam String nome) {
        List<CategoriaDto> categorias = categoriaService.buscarPorNomeCategoria(nome);
        if (categorias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categorias);
        }
    }
}
