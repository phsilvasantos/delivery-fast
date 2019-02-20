package com.br.deliveryfast.controller;

import com.br.deliveryfast.domain.Pedido;
import com.br.deliveryfast.repository.PedidoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by thiagoravt@hotmail.com.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final Logger LOG = Logger.getLogger(PedidoController.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Obtem lista de lanches.
     *
     * @return Colecao de lanches
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar() {
        Collection<Pedido> pedidos = new ArrayList<>();

        try {
            pedidos = pedidoRepository.listarTodos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (final Exception exception) {
            LOG.error("Ocorreu um erro na na requisicao /api/pedidos/listar", exception);
            return new ResponseEntity<>("Ocorreu um erro na listagem de pedidos. Consulte o log para mais detalhes.",
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
