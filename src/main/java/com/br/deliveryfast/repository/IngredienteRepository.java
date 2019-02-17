package com.br.deliveryfast.repository;

import com.br.deliveryfast.domain.Ingrediente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredienteRepository {

    private static final Logger LOG = Logger.getLogger(IngredienteRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Obtem lista de ingreditens.
     *
     * @return List<Ingrediente>
     */
    public List<Ingrediente> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM INGREDIENTE", new BeanPropertyRowMapper<>(Ingrediente.class));
    }

    public List<Ingrediente> buscarIngredientes(long[] idIngredientes) {
        List<Ingrediente> resultadoIngredientes = new ArrayList<>();
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("ids", idIngredientes);

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT * FROM INGREDIENTE WHERE ID IN(");
        for (int i = 0; i < idIngredientes.length; i++) {
            sbQuery.append(idIngredientes[i] + ",");
        }
        sbQuery.deleteCharAt(sbQuery.lastIndexOf(","));
        sbQuery.append(")");

        try {
            //resultadoIngredientes = jdbcTemplate.query("SELECT * FROM INGREDIENTE WHERE ID IN(:ids)", new BeanPropertyRowMapper<>(Ingrediente.class), parameters);
            resultadoIngredientes = jdbcTemplate.query(sbQuery.toString(), new BeanPropertyRowMapper<>(Ingrediente.class));
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao consultar os ingredientes", e);
        }
        return resultadoIngredientes;
    }
}
