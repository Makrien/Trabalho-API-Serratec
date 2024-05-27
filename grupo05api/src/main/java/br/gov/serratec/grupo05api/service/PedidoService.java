package br.gov.serratec.grupo05api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.serratec.grupo05api.dto.ItemPedidoDto;
import br.gov.serratec.grupo05api.dto.PedidoCadastroDto;
import br.gov.serratec.grupo05api.dto.PedidoDto;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.repository.ClienteRepository;
import br.gov.serratec.grupo05api.repository.ItemPedidoRepository;
import br.gov.serratec.grupo05api.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {
	
	@Autowired
	private EmailService email;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;

	public List<PedidoDto> obterTodos() {
		return pedidoRepo.findAll().stream()
				.map(PedidoDto::toDto)
				.collect(Collectors.toList());
	}
	
	public Optional<PedidoDto> obterPorId(Long id) {
		Optional<Pedido> pedidoEntity = pedidoRepo.findById(id);
		if (pedidoEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(pedidoEntity.get()));
	}

	public PedidoDto cadastrar(@Valid PedidoCadastroDto novoPedido) {
		 LocalDate dataAtual = LocalDate.now();		
		 if (novoPedido.dataPedido().isBefore(dataAtual)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data não pode ser retroativa à atual.");
		 }
		Optional<Cliente> clienteOpt = clienteRepo.findById(novoPedido.idCliente());
		if (clienteOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não cadastrado.");
		}
		
		Pedido pedido = novoPedido.toEntity(clienteOpt.get());		
		Pedido pedidoEntity = pedidoRepo.save(pedido);
		return PedidoDto.toDto(pedidoEntity);
	}

	public Optional<PedidoDto> atualizar(Long pedidoId, @Valid PedidoCadastroDto pedidoDto) {
		if (pedidoRepo.existsById(pedidoId)) {
			Optional<Cliente> clienteOpt = clienteRepo.findById(pedidoDto.idCliente());
			if (clienteOpt.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não cadastrado.");
			}
		
			Pedido pedidoEntity = pedidoRepo.findById(pedidoId).orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
			
			pedidoEntity.setDataPedido(pedidoDto.dataPedido());
            pedidoEntity.setDataEntrega(pedidoDto.dataEntrega());
            pedidoEntity.setDataEnvio(pedidoDto.dataEnvio());
            pedidoEntity.setStatus(pedidoDto.status());
            Double totalValorLiquido = pedidoRepo.calcularTotalValorLiquido(pedidoId);
            pedidoEntity.setValorTotal(totalValorLiquido);
            pedidoEntity.setCliente(clienteOpt.get());
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
		itemPedidoRepo.deleteByItemIdPedido(id);
		pedidoRepo.save(pedidoEntity.get());
		pedidoRepo.excluirPedido(id);
		return true;
	}
	public String buscarRelatorioPedido(Long id) {
	    Optional<Pedido> pedidoOptional = pedidoRepo.findById(id);
	    if (pedidoOptional.isPresent()) {
	        Pedido pedido = pedidoOptional.get();
	        List<ItemPedidoDto> itens = itemPedidoRepo.findByItemIdPedido(pedido.getId())
                    .stream()
                    .map(ItemPedidoDto::toDto)
                    .collect(Collectors.toList());
	        PedidoDto pedidoDto = PedidoDto.toDto(pedido);
			StringBuilder itensPedidoString = new StringBuilder();
			for (ItemPedidoDto item : itens) {
				try {
					String pedidoString = 
										 "Código: " + item.produto().getId() + "\n"
										+"Produto: " + item.produto().getNome() +"\n"
										+"Imagem:  " + item.produto().getImagem()+"\n"
										+"Descrição: " + item.produto().getDescricao() +"\n"
										+"Valor Unitário: " + item.precoVenda() + "\n"
										+"Quantidade: " + item.quantidade() + "\n"
										+"Valor Bruto: " +item.valorBruto()+ "\n"
										+"Percentual Desconto: " + item.percentualDesconto()+"%"
										+"\nValor Líquido: R$" + item.valorLiquido() +"\n"
										+"----------------------------------------------";
											
					itensPedidoString.append(pedidoString).append("\n");
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}


			String emailBody = "Olá,"+pedidoDto.cliente().getNomeCompleto()+"\n\n" + "Segue abaixo o relatório do pedido:\n\n" 
								+ "ID do Pedido: "+ pedidoDto.id() + "\n"
								+ "Data do Pedido: " +pedidoDto.dataPedido() + "\n"
								+ "Valor Total: "+ pedidoDto.valorTotal() + "\n" 
								+ "Itens do pedido:\n" + itensPedidoString.toString() + "\n\n" 
								+ "Atenciosamente,\n"
								+ "Equipe de Suporte";
			
	        email.enviarEmail(pedidoDto.cliente().getEmail(), "Relatorio do pedido realizado", emailBody);
	        
	        
	        
	        return emailBody;
	    } else {
	    	return null;
	    }
	}
}

