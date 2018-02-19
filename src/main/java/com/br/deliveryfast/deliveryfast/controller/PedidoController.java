package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Ingrediente;
import com.br.deliveryfast.deliveryfast.domain.Pedido;
import com.br.deliveryfast.deliveryfast.domain.services.PedidoService;
import com.br.deliveryfast.deliveryfast.repository.PedidoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private static final Logger LOG = Logger.getLogger(PedidoController.class);
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoRepository repository){
        this.pedidoRepository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listarTodosIngreditens() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = (List<Pedido>) pedidoRepository.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity criarPedido(@RequestBody final Pedido pedido) {
       try {
           pedidoService.gerarPedido(pedido);
           pedidoRepository.criarPedido(pedido);
           return ResponseEntity.ok(pedido);
       } catch (Exception e){
           LOG.error("Ocorreu um erro ao salvar o ingrediente", e);
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ocorreu um erro ao salvar o ingrediente");
        }
    }


}
