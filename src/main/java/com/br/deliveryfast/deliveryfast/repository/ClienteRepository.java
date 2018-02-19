package com.br.deliveryfast.deliveryfast.repository;

import com.br.deliveryfast.deliveryfast.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Obtem lista de itens do clientes.
     *
     * @return List<Cliente>
     */
    public List<Cliente> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM CLIENTE", new BeanPropertyRowMapper<>(Cliente.class));
    }
}
