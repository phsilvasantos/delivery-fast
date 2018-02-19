package com.br.deliveryfast.deliveryfast.repository;

import com.br.deliveryfast.deliveryfast.domain.Cardapio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardapioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Obtem lista de itens do cardapio.
     *
     * @return List<Cardapio>
     */
    public List<Cardapio> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM CARDAPIO", new BeanPropertyRowMapper<>(Cardapio.class));
    }
}
