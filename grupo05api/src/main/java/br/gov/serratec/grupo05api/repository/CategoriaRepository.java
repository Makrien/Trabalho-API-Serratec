package br.gov.serratec.grupo05api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.serratec.grupo05api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
