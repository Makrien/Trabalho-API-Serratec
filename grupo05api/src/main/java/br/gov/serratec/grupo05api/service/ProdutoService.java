package br.gov.serratec.grupo05api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
                    CategoriaDto.toDto(produto.getCategoria())
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
    
    public ProdutoDto atualizarProduto(Long id, ProdutoDto produtoDto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoDto.nome());
            produto.setDescricao(produtoDto.descricao());
            produto.setQtdEstoque(produtoDto.qtdEstoque());
            produto.setDataCadastro(LocalDate.parse(produtoDto.dataCadastro()));
            produto.setValorUnitario(produtoDto.valorUnitario());
            produto.setImagem(produtoDto.imagem());
            produto.setCategoria(produtoDto.categoria().toEntity());
            Produto produtoAtualizado = produtoRepository.save(produto);
            return ProdutoDto.toDto(produtoAtualizado);
        } else {
            return null;
        }
    }
    
    public Boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    public ProdutoDto obterPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ProdutoDto::toDto).orElse(null);
    }
    
    public List<ProdutoDto> buscarPorNomeProduto(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        return produtos.stream()
                       .map(produtoEntity -> new ProdutoDto(
                                produtoEntity.getId(),
                                produtoEntity.getNome(),
                                produtoEntity.getDescricao(),
                                produtoEntity.getQtdEstoque(),
                                produtoEntity.getDataCadastro().toString(),
                                produtoEntity.getValorUnitario(),
                                produtoEntity.getImagem(),
                                CategoriaDto.toDto(produtoEntity.getCategoria())))
                       .collect(Collectors.toList());
    }
}
