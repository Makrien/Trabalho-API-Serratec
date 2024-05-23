package br.gov.serratec.grupo05api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.PedidoDto;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	public List<PedidoDto> obterTodos() {
		return repositorio.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}

	public PedidoDto cadastrar(@Valid PedidoDto novoPedido) {
		Pedido pedidoEntity = repositorio.save(novoPedido.toEntity());
		return PedidoDto.toDto(pedidoEntity);
	}
	
	
}