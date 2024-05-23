package br.gov.serratec.grupo05api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> obterTodos() {
        List<ProdutoDto> produtos = produtoService.obterTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        ProdutoDto novoProduto = produtoService.cadastrarProduto(produtoDto);
        return ResponseEntity.ok(novoProduto);
    }
}
