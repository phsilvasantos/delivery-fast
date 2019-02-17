package com.br.deliveryfast.repository;

import com.br.deliveryfast.domain.Lanche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio para persitencia de entidades Lanche
 */
@Repository
public class LancheRepository extends com.br.deliveryfast.repository.Repository {

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
        return jdbcTemplate.query("SELECT * FROM LANCHE", new BeanPropertyRowMapper<>(Lanche.class));
    }

    public Lanche add(Lanche lanche) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("descricao", lanche.getDescricao());
        parametros.put("tipoLanche", lanche.getTipoLanche());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        String sql = "INSERT INTO LANCHE(ID, DESCRICAO, TIPO_LANCHE) VALUES(LANCHE_SEQ.nextval, :descricao, :tipoLanche)";

        try{
            int key = namedParameterJdbcTemplate.update(sql, parametros);
            lanche.setId(Long.valueOf(key));
        } catch (Exception e){
            System.out.println("Ocorreu um erro na camada de persistencia");
        }

        return lanche;
    }

    /**
     * Obtem id pela sequence.
     *
     */
    private Long gerarId() {
        //Obtém o ID da sequence
        final String sqlIdNextVal = "select LANCHE_SEQ.nextval from dual";
        Long id = transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(org.springframework.transaction.TransactionStatus status) {
                return jdbcTemplate.queryForObject(sqlIdNextVal, Long.class);
            }
        });
       return id;
    }



}
