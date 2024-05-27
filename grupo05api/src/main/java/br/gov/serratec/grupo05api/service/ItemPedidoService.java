package br.gov.serratec.grupo05api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.gov.serratec.grupo05api.dto.ItemPedidoCadastroDto;
import br.gov.serratec.grupo05api.dto.ItemPedidoDto;
import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.model.Pedido;
import br.gov.serratec.grupo05api.model.Produto;
import br.gov.serratec.grupo05api.repository.ItemPedidoRepository;
import br.gov.serratec.grupo05api.repository.PedidoRepository;
import br.gov.serratec.grupo05api.repository.ProdutoRepository;

@Service
public class ItemPedidoService {
	
    @Autowired
    private ItemPedidoRepository itemPedidoRepo;
    
    @Autowired
    private ProdutoRepository produtoRepo;
    
    @Autowired
    private PedidoRepository pedidoRepo;

    public ItemPedidoDto cadastrar(ItemPedidoCadastroDto novoItemPedido) {
        Optional<Produto> produtoOpt = produtoRepo.findById(novoItemPedido.idProduto());
        if(itemPedidoRepo.existeProduto(novoItemPedido.idPedido(), novoItemPedido.idProduto())) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já cadastrado no pedido");
        }
        
        if (produtoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não cadastrado");
        }

        Optional<Pedido> pedidoOpt = pedidoRepo.findById(novoItemPedido.idPedido());
        if (pedidoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido não cadastrado");
        }

        ItemPedido itemPedido = novoItemPedido.toEntity(produtoOpt.get(), pedidoOpt.get());
        itemPedido.setPrecoVenda(produtoOpt.get().getValorUnitario());
        itemPedido.setValorBruto(produtoOpt.get().getValorUnitario());
        itemPedido.setValorLiquido(itemPedido.getPercentualDesconto());
        ItemPedido itemPedidoEntity = itemPedidoRepo.save(itemPedido);
        Double totalValorLiquido = pedidoRepo.calcularTotalValorLiquido(itemPedido.getPedido().getId());
        pedidoRepo.atualizarValorTotalPedido(itemPedido.getPedido().getId(), totalValorLiquido);

        return ItemPedidoDto.toDto(itemPedidoEntity);
    }

    public Optional<ItemPedidoDto> atualizar(Long id, ItemPedidoCadastroDto itemPedidoDto) {
        if (itemPedidoRepo.existsById(id)) {
            Optional<Produto> produtoOpt = produtoRepo.findById(itemPedidoDto.idProduto());
            if (produtoOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não cadastrado");
            }

            Optional<Pedido> pedidoOpt = pedidoRepo.findById(itemPedidoDto.idPedido());
            if (pedidoOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido não cadastrado");
            }

            ItemPedido itemPedidoEntity = itemPedidoRepo.findById(id).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de Pedido não encontrado"));

            itemPedidoEntity.setQuantidade(itemPedidoDto.quantidade());
            itemPedidoEntity.setPercentualDesconto(itemPedidoDto.percentualDesconto());
            itemPedidoEntity.setProduto(produtoOpt.get());
            itemPedidoEntity.setPedido(pedidoOpt.get());
            itemPedidoRepo.save(itemPedidoEntity);
            return Optional.of(ItemPedidoDto.toDto(itemPedidoEntity));
        }
        return Optional.empty();
    }

    public void deletar(Long id) {
        if (!itemPedidoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de Pedido não encontrado");
        }
        itemPedidoRepo.deleteById(id);
    }

    public Optional<ItemPedidoDto> buscarPorId(Long id) {
        return itemPedidoRepo.findById(id).map(ItemPedidoDto::toDto);
    }

    public List<ItemPedidoDto> listarTodos() {
        return itemPedidoRepo.findAll().stream()
            .map(ItemPedidoDto::toDto)
            .collect(Collectors.toList());
    }
    
 
}
