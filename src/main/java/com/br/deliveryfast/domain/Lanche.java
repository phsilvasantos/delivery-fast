package com.br.deliveryfast.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe que representa entidade Lanche.
 */
public class Lanche {

    private Long id;
    private String descricao;
    private List<Ingrediente> ingredientes;
    private String tipoLanche;
    private BigDecimal valor;

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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTipoLanche() {
        return tipoLanche;
    }

    public void setTipoLanche(String tipoLanche) {
        this.tipoLanche = tipoLanche;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
