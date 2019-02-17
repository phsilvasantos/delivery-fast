package com.br.deliveryfast.repository;

import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.Pedido;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Classe padronizacao de operacoes de CRUD.
 *
 * @param <T> Tipo da Entidade
 */
public abstract class Repository<T> {

    protected final static Log LOGGER = LogFactory.getLog(Repository.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected TransactionTemplate transactionTemplate;

    /**
     * Obtém jdbcTemplate.
     *
     * @return jdbcTemplate.
     */
    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Modifica jdbcTemplate.
     *
     * @param jdbcTemplate novo jdbcTemplate.
     */
    protected void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Obtém transactionTemplate.
     *
     * @return transactionTemplate.
     */
    protected TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * Modifica transactionTemplate.
     *
     * @param transactionTemplate novo transactionTemplate.
     */
    protected void setTransactionTemplate(final TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * Busca pelo id da entidade.
     *
     * @param object objeto a ser encontrado.
     * @return objeto.
     */
    public T findById(final T object) {
        return null;
    }

    /**
     * Atualiza o objeto em sua respectiva tabela.
     *
     * @param object a ser atualizado.
     * @return objeto.
     */
    public T update(final T object) {
        return null;
    }

    /**
     * Adiciona objeto em sua respectiva tabela.
     *
     * @param object objeto a ser adicionado.
     * @return object .
     */
    public T add(final T object) {
        return null;
    }

    /**
     * Remove objeto de sua respectiva tabela.
     *
     * @return obejeto removido.
     */
    public T delete(final T object) {
        return null;
    }



    /**
     * Obtem todos os objetos existentes na respectiva tabela.
     *
     * @return lista de objetos.
     */
    public List<T> listAll() {
        return null;
    }

}
