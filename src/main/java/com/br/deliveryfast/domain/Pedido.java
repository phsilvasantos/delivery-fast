package com.br.deliveryfast.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Pedido {

    private Long id;
    private String cliente;
    private String endereco;
    private Date dtPedido;
    private BigDecimal valor;
    private String status;
    private Collection<Long> idLanches;

    /**
     * Obtém id.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica id.
     *
     * @param id novo id.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Obtém cliente.
     *
     * @return cliente.
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Modifica cliente.
     *
     * @param cliente novo cliente.
     */
    public void setCliente(final String cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtém endereco.
     *
     * @return endereco.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Modifica endereco.
     *
     * @param endereco novo endereco.
     */
    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém dtPedido.
     *
     * @return dtPedido.
     */
    public Date getDtPedido() {
        return dtPedido;
    }

    /**
     * Modifica dtPedido.
     *
     * @param dtPedido novo dtPedido.
     */
    public void setDtPedido(final Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    /**
     * Obtém valor.
     *
     * @return valor.
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Modifica valor.
     *
     * @param valor novo valor.
     */
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * Obtém status.
     *
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Modifica status.
     *
     * @param status novo status.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Obtém idLanches.
     *
     * @return idLanches.
     */
    public Collection<Long> getIdLanches() {
        return idLanches;
    }

    /**
     * Modifica idLanches.
     *
     * @param idLanches novo idLanches.
     */
    public void setIdLanches(final Collection<Long> idLanches) {
        this.idLanches = idLanches;
    }
}
