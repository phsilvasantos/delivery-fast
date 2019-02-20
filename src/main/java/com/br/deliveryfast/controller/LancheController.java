package com.br.deliveryfast.controller;

import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.SolicitacaoLanche;
import com.br.deliveryfast.domain.services.LancheService;
import com.br.deliveryfast.repository.LancheRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by thiagoravt@hotmail.com.
 */
@RestController
@RequestMapping("/api/lanches")
public class LancheController {

    private static final Logger LOG = Logger.getLogger(LancheController.class);

    @Autowired
    private LancheRepository lancheRepository;
    @Autowired
    private LancheService lancheService;

    /**
     * Obtem lista de lanches.
     *
     * @return Colecao de lanches
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar() {
        Collection<Lanche> lanches = new ArrayList<>();

        try {
            lanches = lancheRepository.listarTodos();
            return new ResponseEntity<>(lanches, HttpStatus.OK);
        } catch (final Exception exception) {
            LOG.error("[GET] /api/lanches - {}", exception);
            return new ResponseEntity<>("Ocorreu um erro na listagem de lanches. Consulte o log para mais detalhes.",
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Cria um objeto solicitacaoLanche.
     *
     * @param solicitacaoLanche
     * @return Lanche criado.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity salvar(@RequestBody(required = true) SolicitacaoLanche solicitacaoLanche) {
        try {
            Lanche lanche = lancheService.criarLanche(solicitacaoLanche);
            return new ResponseEntity<>(lanche, HttpStatus.OK);
        } catch (final Exception exception) {
            LOG.error("[POST] /api/lanches - {}", exception);
            return new ResponseEntity<>("Ocorreu um erro na solicitação de um novo lanche. Consulte o log para mais detalhes.",
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remover um objeto Lanche.
     *
     * @param lanche
     * @return Lanche criado.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity remover(@RequestBody(required = true) Lanche lanche) {
        try {
            lancheRepository.delete(lanche);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (final Exception exception) {
            LOG.error("[DELETE] /api/lanches - {}", exception);
            return new ResponseEntity<>("Ocorreu um errona exclusão de lanche. Consulte o log para mais detalhes.",
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
