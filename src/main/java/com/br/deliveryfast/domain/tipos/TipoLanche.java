package com.br.deliveryfast.domain.tipos;

/**
 * Enum para representacao dos tipos de lanche.
 */
public enum TipoLanche {
    /**
     * X_BACON.
     */
    X_BACON("1", "Bacon, hamburguer de carne e queijo"),

    /**
     * X_BURGUER.
     */
    X_BURGUER("2", "Hamburguer de carne e queijo"),

    /**
     * X_EGG.
     */
    X_EGG("3", "Ovo, hamburguer de carne e queijo"),

    /**
     * X_EGG_COM_BACON.
     */
    X_EGG_COM_BACON("4", "Ovo, bacon, hamburguer de carne e queijo"),

    /**
     * X_PERSONALIZADO.
     */
    X_PERSONALIZADO("5", "Monte seu prório lanche !!!");

    private String codigo;
    private String descricao;

    /**
     * Construtor default.
     *
     * @param codigo
     * @param descricao
     */
    TipoLanche(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Obtem o codigo do Enum.
     *
     * @return codigo.
     */
    public String getCodigo() {
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
     * Obtem enum por codigo.
     *
     * @return codigo.
     */
    public static TipoLanche obterPorCodigo(String codigo) {
        for (TipoLanche tipoLanche : TipoLanche.values()) {
            if (tipoLanche.getCodigo().equals(codigo)) {
                return tipoLanche;
            }
        }

        return null;
    }
}
