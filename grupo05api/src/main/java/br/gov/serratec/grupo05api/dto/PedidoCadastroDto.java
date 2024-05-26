package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoCadastroDto(Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataEnvio,
        String status,
        Double valorTotal,
        Long idCliente) {

	public Pedido toEntity(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(this.dataPedido);
        pedido.setDataEntrega(this.dataEntrega);
        pedido.setDataEnvio(this.dataEnvio);
        pedido.setStatus(this.status);
        pedido.setValorTotal(this.valorTotal);
        pedido.setCliente(cliente);
        return pedido;
    }
}
