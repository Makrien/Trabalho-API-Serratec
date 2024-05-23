package br.gov.serratec.grupo05api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.serratec.grupo05api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}