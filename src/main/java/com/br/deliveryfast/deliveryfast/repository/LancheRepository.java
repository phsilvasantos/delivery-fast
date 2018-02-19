package com.br.deliveryfast.deliveryfast.repository;

import com.br.deliveryfast.deliveryfast.domain.Lanche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LancheRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Obtem lista de lanches.
     *
     * @return List<Lanche>
     */
    public List<Lanche> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM LANCHE", new BeanPropertyRowMapper<>(Lanche.class));
    }
}
