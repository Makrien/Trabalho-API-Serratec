package br.gov.serratec.grupo05api.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.dto.PedidoRelatorioDto;
import br.gov.serratec.grupo05api.service.PedidoService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedidos")
    public List<PedidoRelatorioDto> getRelatorioPedidos() {
        return pedidoService.buscarRelatorioPedidos();
    }
}
