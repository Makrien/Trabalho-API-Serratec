package br.gov.serratec.grupo05api.service;

import java.util.List;
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
        Categoria categoria = categoriaDto.toEntity();
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return CategoriaDto.toDto(novaCategoria);
    }
}
