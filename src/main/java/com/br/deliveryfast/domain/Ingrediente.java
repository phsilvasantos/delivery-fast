package com.br.deliveryfast.domain;

import java.math.BigDecimal;

/**
 * Classe que representa ingredientes.
 */
public class Ingrediente {

    private Long id;
    private String descricao;
    private BigDecimal valor;

    public Ingrediente() {
    }

    public Ingrediente(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Ingrediente(Long id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
