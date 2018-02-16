package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAllClientes() {
        List<Cliente> clienteList = new ArrayList<>();
        Cliente cliente = new Cliente();
        clienteList.add(cliente);
        return ResponseEntity.ok(clienteList);
    }
}
