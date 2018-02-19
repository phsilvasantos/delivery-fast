package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Lanche;
import com.br.deliveryfast.deliveryfast.repository.LancheRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lanche")
public class LancheController {

    private static final Logger LOG = Logger.getLogger(LancheController.class);
    private LancheRepository lancheRepository;

    @Autowired
    public LancheController(LancheRepository repository) {
        this.lancheRepository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listarTodosLanches() {
        List<Lanche> lanches = new ArrayList<>();
        lanches = (List<Lanche>) lancheRepository.listarTodos();
        return ResponseEntity.ok(lanches);
    }
}
