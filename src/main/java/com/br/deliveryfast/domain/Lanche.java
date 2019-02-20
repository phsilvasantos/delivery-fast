package com.br.deliveryfast.domain;

import java.math.BigDecimal;

/**
 * Classe que representa entidade Lanche.
 */
public class Lanche {

    private Long id;
    private String descricao;
    private String tipoLanche;
    private BigDecimal valor;
    private BigDecimal valorComDesconto;

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
     * Obtém descricao.
     *
     * @return descricao.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Modifica descricao.
     *
     * @param descricao novo descricao.
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém tipoLanche.
     *
     * @return tipoLanche.
     */
    public String getTipoLanche() {
        return tipoLanche;
    }

    /**
     * Modifica tipoLanche.
     *
     * @param tipoLanche novo tipoLanche.
     */
    public void setTipoLanche(final String tipoLanche) {
        this.tipoLanche = tipoLanche;
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
     * Obtém valorComDesconto.
     *
     * @return valorComDesconto.
     */
    public BigDecimal getValorComDesconto() {
        return valorComDesconto;
    }

    /**
     * Modifica valorComDesconto.
     *
     * @param valorComDesconto novo valorComDesconto.
     */
    public void setValorComDesconto(final BigDecimal valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }
}
