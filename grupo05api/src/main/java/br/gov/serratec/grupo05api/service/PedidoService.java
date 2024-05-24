package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<PedidoDto> atualizar(Long id, @Valid PedidoDto pedido) {
		if (repositorio.existsById(id)) {
			Pedido pedidoEntity = pedido.toEntity();
			pedidoEntity.setId(id);
			repositorio.save(pedidoEntity);
			return Optional.of(PedidoDto.toDto(pedidoEntity));
		}
		return Optional.empty();
	}

	public boolean excluir(Long id) {
		Optional<Pedido> pedidoEntity = repositorio.findById(id);
		
		if (pedidoEntity.isEmpty()) {
			return false;
		}
		pedidoEntity.get().getItensPedido().clear();
		repositorio.save(pedidoEntity.get());
		repositorio.excluirPedido(id);
		return true;
	}

	public Optional<PedidoDto> obterPorId(Long id) {
		Optional<Pedido> pedidoEntity = repositorio.findById(id);
		if (pedidoEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(pedidoEntity.get()));
	}
	
	
}