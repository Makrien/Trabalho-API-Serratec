package br.gov.serratec.grupo05api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.serratec.grupo05api.service.PedidoService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public String getRelatorioPedidos(@PathVariable Long id) {
        return pedidoService.buscarRelatorioPedido(id);
    }
}
