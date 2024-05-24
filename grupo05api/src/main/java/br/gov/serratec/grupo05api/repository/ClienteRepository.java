package br.gov.serratec.grupo05api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.serratec.grupo05api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
