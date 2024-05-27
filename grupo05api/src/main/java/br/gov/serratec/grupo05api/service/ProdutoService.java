package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.serratec.grupo05api.dto.ProdutoCadastroDto;
import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.model.Categoria;
import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.CategoriaRepository;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepo;
    
    @Autowired
    private CategoriaRepository categoriaRepo;

    public ProdutoDto cadastrar(ProdutoCadastroDto novoProduto) {
        Optional<Categoria> categoriaOpt = categoriaRepo.findById(novoProduto.idCategoria());
        if (categoriaOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não cadastrada");
        }

        Produto produto = novoProduto.toEntity(categoriaOpt.get());
        Produto produtoEntity = produtoRepo.save(produto);
        return ProdutoDto.toDto(produtoEntity);
    }

    public Optional<ProdutoDto> atualizar(Long id, ProdutoCadastroDto produtoDto) {
        if (produtoRepo.existsById(id)) {
            Optional<Categoria> categoriaOpt = categoriaRepo.findById(produtoDto.idCategoria());
            if (categoriaOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não cadastrada");
            }

            Produto produtoEntity = produtoRepo.findById(id).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            produtoEntity.setNome(produtoDto.nome());
            produtoEntity.setDescricao(produtoDto.descricao());
            produtoEntity.setQtdEstoque(produtoDto.qtdEstoque());
            produtoEntity.setDataCadastro(produtoDto.dataCadastro());
            produtoEntity.setValorUnitario(produtoDto.valorUnitario());
            produtoEntity.setImagem(produtoDto.imagem());
            produtoEntity.setCategoria(categoriaOpt.get());

            produtoRepo.save(produtoEntity);
            return Optional.of(ProdutoDto.toDto(produtoEntity));
        }
        return Optional.empty();
    }

    public void deletar(Long id) {
        if (!produtoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtoRepo.deleteById(id);
    }

    public Optional<ProdutoDto> buscarPorId(Long id) {
        return produtoRepo.findById(id).map(ProdutoDto::toDto);
    }

    public List<ProdutoDto> buscarPorNomeProduto(String nome) {
        List<Produto> produtos = produtoRepo.findByNomeContainingIgnoreCase(nome);
        return produtos.stream()
                .map(ProdutoDto::toDto)
                .collect(Collectors.toList());
    }
  
    public List<ProdutoDto> listarTodos() {
        return produtoRepo.findAll().stream()
            .map(ProdutoDto::toDto)
            .collect(Collectors.toList());
    }
   
}
