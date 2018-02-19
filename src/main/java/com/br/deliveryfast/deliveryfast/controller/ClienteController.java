package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Cliente;
import com.br.deliveryfast.deliveryfast.repository.ClienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    private static final Logger LOG = Logger.getLogger(IngredienteController.class);
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository repository){
        this.clienteRepository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAllClientes() {
        List<Cliente> clienteList = new ArrayList<>();
        Cliente cliente = new Cliente(new Long(1L), "Teste");
        clienteList.add(cliente);
        return ResponseEntity.ok(clienteList);
    }
}
