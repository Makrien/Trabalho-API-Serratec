package br.gov.serratec.grupo05api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.serratec.grupo05api.model.Pedido;
import jakarta.transaction.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Modifying
	@Transactional
	@Query("delete from Pedido p where p.id = :idPedido")
	void excluirPedido(@Param("idPedido") Long idPedido);

}