package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.CategoriaDto;
import br.gov.serratec.grupo05api.model.Categoria;
import br.gov.serratec.grupo05api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> obterTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                         .map(CategoriaDto::toDto)
                         .collect(Collectors.toList());
    }

    public CategoriaDto cadastrarCategoria(CategoriaDto categoriaDto) {
    	Optional<Categoria> categoriaExistente = categoriaRepository
    			.findByNomeContainingIgnoreCase(categoriaDto.nome()).stream().findFirst();
        if (categoriaExistente.isPresent()) {
            return null;
        }

        Categoria categoria = categoriaDto.toEntity();
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(novaCategoria);
    }
    
    public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNome(categoriaDto.nome());
            categoria.setDescricao(categoriaDto.descricao());
            Categoria categoriaAtualizada = categoriaRepository.save(categoria);
            return CategoriaDto.toDto(categoriaAtualizada);
        } else {
            return null;
        }
    }
    
    public Boolean deletarCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    public CategoriaDto obterPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(CategoriaDto::toDto).orElse(null);
    }
    
    public List<CategoriaDto> buscarPorNomeCategoria(String nome) {
        List<Categoria> categorias = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        return categorias.stream()
                         .map(CategoriaDto::toDto)
                         .collect(Collectors.toList());
    }
}
