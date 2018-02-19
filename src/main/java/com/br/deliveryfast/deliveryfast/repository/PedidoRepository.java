package com.br.deliveryfast.deliveryfast.repository;

import com.br.deliveryfast.deliveryfast.domain.Pedido;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PedidoRepository {

    private static final Logger LOG = Logger.getLogger(PedidoRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Obtem lista de pedidos.
     *
     * @return List<Pedido>
     */
    public List<Pedido> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM PEDIDO", new BeanPropertyRowMapper<>(Pedido.class));
    }

    @Transactional
    public void criarPedido(Pedido pedido) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("idCliente", pedido.getId());
        parametros.addValue("valor", pedido.getValor());
        parametros.addValue("dt_pedido", pedido.getDtPedido());

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("INSERT INTO PEDIDO (ID, ID_CLIENTE, VALOR, DT_PEDIDO) VALUES (PEDIDO_SEQ.NEXTVAL, :idCliente, :valor, :dt_pedido)");
        sbQuery.append("," + pedido.getIdCliente());
        sbQuery.append("," + pedido.getValor());
        sbQuery.append("," + pedido.getDtPedido());

        try {
            jdbcTemplate.update(sbQuery.toString());
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao criar o pedido", e);
        }
    }
}
