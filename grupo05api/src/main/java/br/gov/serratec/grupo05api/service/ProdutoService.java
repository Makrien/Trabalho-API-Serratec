package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                       .map(ProdutoDto::toDto)
                       .collect(Collectors.toList());
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        Produto produto = produtoDto.toEntity();
        Produto novoProduto = produtoRepository.save(produto);
        return ProdutoDto.toDto(novoProduto);
    }
}
