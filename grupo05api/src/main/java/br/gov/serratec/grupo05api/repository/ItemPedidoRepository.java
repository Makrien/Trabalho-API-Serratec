package br.gov.serratec.grupo05api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.serratec.grupo05api.model.ItemPedido;
import jakarta.transaction.Transactional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{


	@Query("SELECT ip FROM ItemPedido ip WHERE ip.pedido.id = :idPedido")
	List<ItemPedido> findByItemIdPedido(@Param("idPedido") Long idPedido);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM ItemPedido ip WHERE ip.pedido.id = :idPedido")
	void deleteByItemIdPedido(@Param("idPedido") Long idPedido);
	
	@Query("SELECT CASE WHEN COUNT(ip) > 0 THEN true ELSE false END "
			+ "FROM ItemPedido ip "
			+ "WHERE ip.pedido.id = :pedidoId AND ip.produto.id = :produtoId")
	 boolean existeProduto(@Param("pedidoId") Long pedidoId, @Param("produtoId") Long produtoId);
}

