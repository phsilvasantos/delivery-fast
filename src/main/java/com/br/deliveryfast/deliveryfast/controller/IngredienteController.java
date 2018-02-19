package com.br.deliveryfast.deliveryfast.controller;

import com.br.deliveryfast.deliveryfast.domain.Ingrediente;
import com.br.deliveryfast.deliveryfast.repository.IngredienteRepository;
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
@RequestMapping("/api/ingrediente")
public class IngredienteController {

    private static final Logger LOG = Logger.getLogger(IngredienteController.class);
    private IngredienteRepository ingredienteRepository;

    @Autowired
    public IngredienteController(IngredienteRepository repository){
        this.ingredienteRepository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listarTodosIngreditens() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes = (List<Ingrediente>) ingredienteRepository.listarTodos();
        return ResponseEntity.ok(ingredientes);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity inserirIngrediente(@RequestBody final Ingrediente ingrediente) {
//       try {
//           ingredienteRepository.save(ingrediente);
//           return ResponseEntity.ok(ingrediente);
//       } catch (Exception e){
//           LOG.error("Ocorreu um erro ao salvar o ingrediente", e);
//           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ocorreu um erro ao salvar o ingrediente");
//        }
//    }
}
