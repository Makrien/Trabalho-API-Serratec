package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import br.gov.serratec.grupo05api.config.Mapper;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoDto(Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataEnvio,
        String status,
        Double valorTotal,
        Cliente cliente,
        List<ItemPedidoDto> itensPedido) {

	public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(this.dataPedido);
        pedido.setDataEntrega(this.dataEntrega);
        pedido.setDataEnvio(this.dataEnvio);
        pedido.setStatus(this.status);
        pedido.setValorTotal(this.valorTotal);
        pedido.setCliente(this.cliente);
        pedido.setItensPedido(this.itensPedido.stream().map(i -> i.toEntity()).toList());
        return pedido;
    }

    public static PedidoDto toDto(Pedido pedidoEntity) {
        return Mapper.getMapper().convertValue(pedidoEntity, PedidoDto.class);
    }
}
