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

import br.gov.serratec.grupo05api.dto.ProdutoCategoriaDto;
import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.service.ProdutoService;
import jakarta.validation.Valid;

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
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> obterPorId(@PathVariable Long id) {
        ProdutoDto produto = produtoService.obterPorId(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produto);
        }
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoDto>> buscarPorNomeProduto(@RequestParam String nome) {
        List<ProdutoDto> produtos = produtoService.buscarPorNomeProduto(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produtos);
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrarProduto(@Valid @RequestBody ProdutoCategoriaDto produtoCategoriaDto) {
		ProdutoDto novoProduto = produtoService.cadastrarProduto(produtoCategoriaDto);
	    return ResponseEntity.ok(novoProduto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoCategoriaDto produtoCategoriaDto) {
    	ProdutoDto produtoAtualizado = produtoService.atualizarProduto(id, produtoCategoriaDto);
        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produtoAtualizado);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        Boolean deletado = produtoService.deletarProduto(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
