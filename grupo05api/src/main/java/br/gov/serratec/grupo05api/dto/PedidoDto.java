package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.model.Produto;

public record PedidoDto(Long id,
        String dataPedido,
        String dataEntrega,
        String dataEnvio,
        String status,
        Double valorTotal,
        List<ProdutoDto> produtos) {

	public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.parse(this.dataPedido));
        pedido.setDataEntrega(LocalDate.parse(this.dataEntrega));
        pedido.setDataEnvio(LocalDate.parse(this.dataEnvio));
        pedido.setStatus(this.status);
        pedido.setValorTotal(this.valorTotal);
        return pedido;
    }
	
    public PedidoDto toDto(Pedido pedidoEntity) {
        return new PedidoDto(
        			pedidoEntity.getId(),
        			pedidoEntity.getDataPedido().toString(),
        			pedidoEntity.getDataEntrega().toString(),
        			pedidoEntity.getDataEnvio().toString(),
        			pedidoEntity.getStatus(),
        			pedidoEntity.getValorTotal(),
        			pedidoEntity.getProdutos()
        		);
    }

}
