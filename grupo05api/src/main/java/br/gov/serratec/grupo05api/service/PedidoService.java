package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.serratec.grupo05api.dto.PedidoDto;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.repository.ClienteRepository;
import br.gov.serratec.grupo05api.repository.ItemPedidoRepository;
import br.gov.serratec.grupo05api.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public List<PedidoDto> obterTodos() {
		return pedidoRepo.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}

	public PedidoDto cadastrar(@Valid PedidoDto novoPedido) {
		Optional<Cliente> clienteOpt = clienteRepo.findById(novoPedido.idCliente());
		if (clienteOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não cadastrado.");
		}
		
		List<ItemPedido> itens = novoPedido.idsItemPedido().stream()
				.map(id -> itemPedidoRepo.findById(id).orElseThrow(() ->
					new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não cadastrado para registro em pedido.")))
				.collect(Collectors.toList());
		
		Pedido pedido = novoPedido.toEntity(clienteOpt.get(), itens);
		
		Pedido pedidoEntity = pedidoRepo.save(pedido);
		return PedidoDto.toDto(pedidoEntity);
	}

	public Optional<PedidoDto> atualizar(Long pedidoId, @Valid PedidoDto pedidoDto) {
		if (pedidoRepo.existsById(pedidoId)) {
			Optional<Cliente> clienteOpt = clienteRepo.findById(pedidoDto.idCliente());
			if (clienteOpt.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não cadastrado.");
			}
			
			List<ItemPedido> itens = pedidoDto.idsItemPedido().stream()
				.map(itemId -> itemPedidoRepo.findById(itemId).orElseThrow(() -> 
					new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não cadastrado para registro em pedido.")))
				.collect(Collectors.toList());
		
				Pedido pedidoEntity = pedidoRepo.findById(pedidoId).orElseThrow(() -> 
					new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
				
				pedidoEntity.setDataPedido(pedidoDto.dataPedido());
	            pedidoEntity.setDataEntrega(pedidoDto.dataEntrega());
	            pedidoEntity.setDataEnvio(pedidoDto.dataEnvio());
	            pedidoEntity.setStatus(pedidoDto.status());
	            pedidoEntity.setValorTotal(pedidoDto.valorTotal());
	            pedidoEntity.setCliente(clienteOpt.get());
	            pedidoEntity.setItensPedido(itens);

	            pedidoRepo.save(pedidoEntity);
	            return Optional.of(PedidoDto.toDto(pedidoEntity));
	        }
	        return Optional.empty();
	}

	public boolean excluir(Long id) {
		Optional<Pedido> pedidoEntity = pedidoRepo.findById(id);
		
		if (pedidoEntity.isEmpty()) {
			return false;
		}
		pedidoEntity.get().getItensPedido().clear();
		pedidoRepo.save(pedidoEntity.get());
		pedidoRepo.excluirPedido(id);
		return true;
	}

	public Optional<PedidoDto> obterPorId(Long id) {
		Optional<Pedido> pedidoEntity = pedidoRepo.findById(id);
		if (pedidoEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(pedidoEntity.get()));
	}
	
	
}