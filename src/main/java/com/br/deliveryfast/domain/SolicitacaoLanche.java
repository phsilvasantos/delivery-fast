package com.br.deliveryfast.domain;

/**
 * Classe que representa uma solicitacao de lanche.
 */
public class SolicitacaoLanche {

    private String codigoLanche;
    private int qtdAlface;
    private int qtdBacon;
    private int qtdHamburguer;
    private int qtdOvo;
    private int qtdQueijo;

    /**
     * Obtém codigoLanche.
     *
     * @return codigoLanche.
     */
    public String getCodigoLanche() {
        return codigoLanche;
    }

    /**
     * Modifica codigoLanche.
     *
     * @param codigoLanche novo codigoLanche.
     */
    public void setCodigoLanche(final String codigoLanche) {
        this.codigoLanche = codigoLanche;
    }

    /**
     * Obtém qtdAlface.
     *
     * @return qtdAlface.
     */
    public int getQtdAlface() {
        return qtdAlface;
    }

    /**
     * Modifica qtdAlface.
     *
     * @param qtdAlface novo qtdAlface.
     */
    public void setQtdAlface(final int qtdAlface) {
        this.qtdAlface = qtdAlface;
    }

    /**
     * Obtém qtdBacon.
     *
     * @return qtdBacon.
     */
    public int getQtdBacon() {
        return qtdBacon;
    }

    /**
     * Modifica qtdBacon.
     *
     * @param qtdBacon novo qtdBacon.
     */
    public void setQtdBacon(final int qtdBacon) {
        this.qtdBacon = qtdBacon;
    }

    /**
     * Obtém qtdHamburguer.
     *
     * @return qtdHamburguer.
     */
    public int getQtdHamburguer() {
        return qtdHamburguer;
    }

    /**
     * Modifica qtdHamburguer.
     *
     * @param qtdHamburguer novo qtdHamburguer.
     */
    public void setQtdHamburguer(final int qtdHamburguer) {
        this.qtdHamburguer = qtdHamburguer;
    }

    /**
     * Obtém qtdOvo.
     *
     * @return qtdOvo.
     */
    public int getQtdOvo() {
        return qtdOvo;
    }

    /**
     * Modifica qtdOvo.
     *
     * @param qtdOvo novo qtdOvo.
     */
    public void setQtdOvo(final int qtdOvo) {
        this.qtdOvo = qtdOvo;
    }

    /**
     * Obtém qtdQueijo.
     *
     * @return qtdQueijo.
     */
    public int getQtdQueijo() {
        return qtdQueijo;
    }

    /**
     * Modifica qtdQueijo.
     *
     * @param qtdQueijo novo qtdQueijo.
     */
    public void setQtdQueijo(final int qtdQueijo) {
        this.qtdQueijo = qtdQueijo;
    }
}
