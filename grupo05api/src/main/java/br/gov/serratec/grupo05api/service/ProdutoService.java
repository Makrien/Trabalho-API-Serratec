package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.ProdutoCategoriaDto;
import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.model.Categoria;
import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.CategoriaRepository;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private CategoriaRepository categoriaRepository;

	public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(p -> ProdutoDto.toDto(p)).toList();
    }

    public ProdutoDto cadastrarProduto(ProdutoCategoriaDto produtoCategoriaDto) {
    	Categoria categoria = categoriaRepository.findById(produtoCategoriaDto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Produto produto = produtoCategoriaDto.toEntity(categoria);
        Produto novoProduto = produtoRepository.save(produto);
        return ProdutoDto.toDto(novoProduto);
    }
    
    public ProdutoDto atualizarProduto(Long id, ProdutoCategoriaDto produtoCategoriaDto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoCategoriaDto.nome());
            produto.setDescricao(produtoCategoriaDto.descricao());
            produto.setQtdEstoque(produtoCategoriaDto.qtdEstoque());
            produto.setDataCadastro(produtoCategoriaDto.dataCadastro());
            produto.setValorUnitario(produtoCategoriaDto.valorUnitario());
            produto.setImagem(produtoCategoriaDto.imagem());
            
            Categoria categoria = categoriaRepository.findById(produtoCategoriaDto.categoriaId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
            produto.setCategoria(categoria);

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
                .map(ProdutoDto::toDto)
                .collect(Collectors.toList());
    }
}
