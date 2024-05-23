package br.gov.serratec.grupo05api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.serratec.grupo05api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
