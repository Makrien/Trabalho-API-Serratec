package br.gov.serratec.grupo05api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
    private ProdutoRepository repositorio;

    @GetMapping
    public List<Produto> obterTodos() {
        return repositorio.findAll();
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        repositorio.save(produto);
        return produto;
    }
}
