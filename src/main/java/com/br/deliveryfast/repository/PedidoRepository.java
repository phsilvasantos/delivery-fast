package com.br.deliveryfast.repository;

import com.br.deliveryfast.domain.Pedido;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsavel por operacoes de CRUD para entidade Pedido
 */
@Repository
public class PedidoRepository extends com.br.deliveryfast.repository.Repository {

    private static final Logger LOG = Logger.getLogger(PedidoRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Construtor default.
     */
    public PedidoRepository() {
        super();
    }

    /**
     * Obtem lista de pedidos.
     *
     * @return List<Pedido>
     */
    public List<Pedido> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM PEDIDOS", new BeanPropertyRowMapper<>(Pedido.class));
    }

    @Transactional
    public Pedido add(Pedido pedido) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente", pedido.getCliente());
        parametros.put("endereco", pedido.getEndereco());
        parametros.put("valorLanche", pedido.getValor());
        parametros.put("status", pedido.getStatus());

        try {
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
            String sql = "INSERT INTO PEDIDOS(ID, CLIENTE, ENDERECO, DT_PEDIDO, VALOR, STATUS) VALUES(PEDIDOS_SEQ.nextval, :cliente, :endereco, SYSDATE, :valorLanche, :status)";

            int key = namedParameterJdbcTemplate.update(sql, parametros);

            //            int key = namedParameterJdbcTemplate.update(sql, parametros);
            pedido.setId(Long.valueOf(key));

            // Persisto em tabela relacional os respectivos lanches que compoem um pedido.
            if (pedido.getIdLanches() != null && !pedido.getIdLanches().isEmpty()) {
                parametros.clear();
                parametros.put("idPedido", pedido.getId());

                for (Long idLanche : pedido.getIdLanches()) {
                    parametros.put("idLanche", idLanche);
                    namedParameterJdbcTemplate.update("INSERT INTO PEDIDOS_LANCHES(ID_PEDIDO, ID_LANCHE) VALUES(:idPedido, :idLanche)", parametros);
                }
            }
        } catch (Exception e) {
            LOG.error("Ocorreu um erro na persistencia do pedidos", e);
        }

        return pedido;
    }

    //    @Transactional
    //    public void criarPedido(Pedido pedido) {
    //        MapSqlParameterSource parametros = new MapSqlParameterSource();
    //        parametros.addValue("idCliente", pedido.getId());
    //        parametros.addValue("valor", pedido.getValor());
    //        parametros.addValue("dt_pedido", pedido.getDtPedido());
    //
    //        StringBuilder sbQuery = new StringBuilder();
    //        sbQuery.append("INSERT INTO PEDIDO (ID, ID_CLIENTE, VALOR, DT_PEDIDO) VALUES (PEDIDO_SEQ.NEXTVAL, :idCliente, :valor, :dt_pedido)");
    //        sbQuery.append("," + pedido.getIdCliente());
    //        sbQuery.append("," + pedido.getValor());
    //        sbQuery.append("," + pedido.getDtPedido());
    //
    //        try {
    //            jdbcTemplate.update(sbQuery.toString());
    //        } catch (Exception e) {
    //            LOG.error("Ocorreu um erro ao criar o pedido", e);
    //        }
    //    }

}
