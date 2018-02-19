package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Cardapio;
import com.br.deliveryfast.deliveryfast.repository.CardapioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    private static final Logger LOG = Logger.getLogger(CardapioController.class);

    private CardapioRepository cardapioRepository;

    @Autowired
    public CardapioController(CardapioRepository repository) {
        this.cardapioRepository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listarTodosItensCardapio() {
        List<Cardapio> itensCardapio = new ArrayList<>();
        itensCardapio = (List<Cardapio>) cardapioRepository.listarTodos();
        return ResponseEntity.ok(itensCardapio);
    }
}
