package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.serratec.grupo05api.model.Pedido;

public record PedidoDto(Long id,
        LocalDate dataPedido,
        LocalDate dataEntrega,
        LocalDate dataEnvio,
        String status,
        Double valorTotal,
        List<ProdutoDto> produtos) {

    public Pedido toEntity() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Pedido.class);
    }

    public static PedidoDto toDto(Pedido pedidoEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(pedidoEntity, PedidoDto.class);
    }
}
