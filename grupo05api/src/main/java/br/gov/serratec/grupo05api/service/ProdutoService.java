package br.gov.serratec.grupo05api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.CategoriaDto;
import br.gov.serratec.grupo05api.dto.ItemPedidoDto;
import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;

	public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDto> produtoDtos = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoDto produtoDto = new ProdutoDto(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getQtdEstoque(),
                    produto.getDataCadastro().toString(),
                    produto.getValorUnitario(),
                    produto.getImagem(),
                    CategoriaDto.toDto(produto.getCategoria()),
                    produto.getItemPedido() != null ? produto.getItemPedido().stream()
                            .map(ItemPedidoDto::toDto)
                            .collect(Collectors.toList()) : new ArrayList<>()
            );
            produtoDtos.add(produtoDto);
        }
        return produtoDtos;
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        Produto produto = produtoDto.toEntity();
        Produto novoProduto = produtoRepository.save(produto);
        return ProdutoDto.toDto(novoProduto);
    }
}
