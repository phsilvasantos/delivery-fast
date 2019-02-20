package com.br.deliveryfast.domain.tipos;

import java.math.BigDecimal;

/**
 * Enum com detalhes dos ingrediente permitidos para elaboracao dos lanches.
 */
public enum TipoIngrediente {

    /**
     * 'Alface'.
     */
    ALFACE(1, "Alface", new BigDecimal(0.40)),

    /**
     * 'Bacon'.
     */
    BACON(2, "Bacon", new BigDecimal(2.0)),

    /**
     * 'Hamburguer de carne'.
     */
    HAMBURGUER(3, "Hamburguer", new BigDecimal(3.0)),

    /**
     * 'Ovo'.
     */
    OVO(4, "Ovo", new BigDecimal(0.80)),

    /**
     * 'Queijo'.
     */
    QUEIJO(5, "Queijo", new BigDecimal(1.5));

    private int codigo;
    private String descricao;
    private BigDecimal valor;

    /**
     * Construtor default.
     *
     * @param codigo
     * @param descricao
     * @param valor
     */
    TipoIngrediente(final int codigo, final String descricao, final BigDecimal valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    /**
     * Obtem o codigo do Enum.
     *
     * @return codigo.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtem o descricao do Enum.
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
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
    void setValor(final BigDecimal valor) {
        this.valor = valor;
    }
}
