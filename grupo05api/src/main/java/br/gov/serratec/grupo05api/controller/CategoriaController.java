package br.gov.serratec.grupo05api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.CategoriaDto;
import br.gov.serratec.grupo05api.service.CategoriaService;

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
	
	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto novaCategoria = categoriaService.cadastrarCategoria(categoriaDto);
        return ResponseEntity.ok(novaCategoria);
	}
}
