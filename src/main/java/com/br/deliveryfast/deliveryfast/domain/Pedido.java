package com.br.deliveryfast.deliveryfast.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Pedido {

    private Long id;
    private Long idCliente;
    private List<Cardapio> itensCardapio;
    private Date dtPedido;
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Cardapio> getItensCardapio() {
        return itensCardapio;
    }

    public void setItensCardapio(List<Cardapio> itensCardapio) {
        this.itensCardapio = itensCardapio;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
