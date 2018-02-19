package com.br.deliveryfast.deliveryfast.domain;

import java.util.List;

public class Cardapio {

    private Long id;
    private String tipoLanche;
    private String descricao;
    private List<Ingrediente> adicionais;


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

    public String getTipoLanche() {
        return tipoLanche;
    }

    public void setTipoLanche(String tipoLanche) {
        this.tipoLanche = tipoLanche;
    }

    public List<Ingrediente> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Ingrediente> adicionais) {
        this.adicionais = adicionais;
    }
}
