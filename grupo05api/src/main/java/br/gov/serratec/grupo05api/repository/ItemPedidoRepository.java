package br.gov.serratec.grupo05api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.serratec.grupo05api.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

//	@Query("SELECT ip.pedido.id FROM ItemPedido ip WHERE ip.pedido.id = :idPedido")
//    List<ItemPedido> findByItemIdPedido(@Param("idPedido") Long idPedido);
	@Query("SELECT ip FROM ItemPedido ip WHERE ip.pedido.id = :idPedido")
	List<ItemPedido> findByItemIdPedido(@Param("idPedido") Long idPedido);
}
