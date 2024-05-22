package br.gov.serratec.grupo05api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.model.Categoria;
import br.gov.serratec.grupo05api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repositorio;
	
	@GetMapping
	public List<Categoria> obterTodos() {
		return repositorio.findAll();
	}
	
	@PostMapping
	public Categoria cadastrarLivro(@RequestBody Categoria categoria) {
		repositorio.save(categoria);
		return categoria;
	}
}
