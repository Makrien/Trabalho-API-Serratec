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

	@Query("SELECT SUM(ip.valorLiquido) FROM ItemPedido ip WHERE ip.pedido.id = :idPedido")
	Double calcularTotalValorLiquido(@Param("idPedido") Long idPedido);
	
	@Modifying
	@Transactional
	@Query("UPDATE Pedido p SET p.valorTotal = :valorTotal WHERE p.id = :idPedido")
	void atualizarValorTotalPedido(@Param("idPedido") Long idPedido, @Param("valorTotal") Double valorTotal);

}