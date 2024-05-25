package br.gov.serratec.grupo05api.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.serratec.grupo05api.dto.ItemPedidoDto;
import br.gov.serratec.grupo05api.dto.PedidoDto;
import br.gov.serratec.grupo05api.dto.ProdutoDto;
import br.gov.serratec.grupo05api.model.ItemPedido;
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
		 LocalDate dataAtual = LocalDate.now();		
		 if (novoPedido.dataPedido().isBefore(dataAtual)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data não pode ser retroativa à atual.");
		}
		
		novoPedido.itensPedido().stream().map(i -> new ItemPedidoDto(
			i.id(),
	        i.quantidade(),
	        i.precoVenda(),
	        i.percentualDesconto(),
	        i.precoVenda() * i.quantidade(),
	        i.valorBruto() - i.percentualDesconto(),
	        i.produto()
		));
		 
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