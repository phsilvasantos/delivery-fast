package com.br.deliveryfast.repository;

import com.br.deliveryfast.domain.Lanche;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio para persitencia de entidades Lanche
 */
@Repository
public class LancheRepository {

    private static final Logger LOG = Logger.getLogger(LancheRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Construtor default.
     */
    public LancheRepository() {
        super();
    }

    /**
     * Obtem lista de lanches.
     *
     * @return List<Lanche>
     */
    public List<Lanche> listarTodos() {
        return jdbcTemplate.query("SELECT * FROM LANCHE ORDER BY ID DESC", new BeanPropertyRowMapper<>(Lanche.class));
    }

    /**
     * Adiciona um objeto lanche em sua respectiva tabela.
     *
     * @param lanche
     * @return Lanche persistido
     */
    public Lanche add(Lanche lanche) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("descricao", lanche.getDescricao());
        parametros.put("tipoLanche", lanche.getTipoLanche());
        parametros.put("valor", lanche.getValor());
        parametros.put("valorComDesconto", lanche.getValorComDesconto());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        String sql = "INSERT INTO LANCHE(ID, DESCRICAO, TIPO_LANCHE, VALOR, VALOR_COM_DESCONTO) VALUES(LANCHE_SEQ.nextval, :descricao, :tipoLanche, :valor, :valorComDesconto)";

        try {
            int key = namedParameterJdbcTemplate.update(sql, parametros);
            lanche.setId(Long.valueOf(key));
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na camada de persistencia");
        }

        return lanche;
    }

    /**
     * Remove um objeto lanche em sua respectiva tabela.
     *
     * @param lanche
     */
    public void delete(Lanche lanche) {
        jdbcTemplate.execute("DELETE FROM LANCHE WHERE ID = " + lanche.getId());
    }

    /**
     * Obtem uma entidade Lanche consultando por sua key.
     *
     * @param id
     * @return Lanche
     */
    public Lanche getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM LANCHE WHERE ID = " + id, (rs, rowNum) -> {
                Lanche lanche = new Lanche();
                lanche.setId(rs.getLong("ID"));
                lanche.setDescricao(rs.getString("DESCRICAO"));
                lanche.setTipoLanche(rs.getString("TIPO_LANCHE"));
                lanche.setValor(rs.getBigDecimal("VALOR"));
                lanche.setValorComDesconto(rs.getBigDecimal("VALOR_COM_DESCONTO"));
                return lanche;
            });
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Objeto não retornado pela key informada", e);
            return null;
        }
    }

}
