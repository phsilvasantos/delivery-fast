package com.br.deliveryfast.domain.services;

import com.br.deliveryfast.domain.Ingrediente;
import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.SolicitacaoLanche;
import com.br.deliveryfast.domain.tipos.TipoIngrediente;
import com.br.deliveryfast.domain.tipos.TipoLanche;
import com.br.deliveryfast.repository.LancheRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.br.deliveryfast.domain.tipos.TipoLanche.*;

/**
 * Classe responsalvel em orquestrar as regras de negocio da entidade Lanche.
 */
@Service
public class LancheService {
    private static final Logger LOG = Logger.getLogger(LancheService.class);

    @Autowired
    private LancheRepository lancheRepository;

    /**
     * Metodo responsavel por criar lanches de acordo a solicitacao recebida.
     *
     * @param solicitacaoLanche
     * @return Lanche
     */
    public Lanche criarLanche(SolicitacaoLanche solicitacaoLanche) {
        Lanche lanche = new Lanche();
        final TipoLanche tipoLanche = TipoLanche.obterPorCodigo(solicitacaoLanche.getCodigoLanche());
        try {
            switch (tipoLanche) {
                case X_BACON:
                    lanche.setTipoLanche(X_BACON.getCodigo());
                    lanche.setDescricao(X_BACON.getDescricao());
                    lanche.setValor(calcularPrecoSemDesconto(solicitacaoLanche));
                    lanche.setValorComDesconto(calcularPrecoDesconto(solicitacaoLanche));
                    //TODO Adicionar os ingredientes do lanche no objeto e persistir em uma tabela relacional Lanche X Ingredientes
                    break;

                case X_BURGUER:
                    lanche.setTipoLanche(X_BURGUER.getCodigo());
                    lanche.setDescricao(X_BURGUER.getDescricao());
                    lanche.setValor(calcularPrecoSemDesconto(solicitacaoLanche));
                    lanche.setValorComDesconto(calcularPrecoDesconto(solicitacaoLanche));
                    //TODO Adicionar os ingredientes do lanche no objeto e persistir em uma tabela relacional Lanche X Ingredientes
                    break;

                case X_EGG:
                    lanche.setTipoLanche(X_EGG.getCodigo());
                    lanche.setDescricao(X_EGG.getDescricao());
                    lanche.setValor(calcularPrecoSemDesconto(solicitacaoLanche));
                    lanche.setValorComDesconto(calcularPrecoDesconto(solicitacaoLanche));
                    //TODO Adicionar os ingredientes do lanche no objeto e persistir em uma tabela relacional Lanche X Ingredientes
                    break;

                case X_EGG_COM_BACON:
                    lanche.setTipoLanche(X_EGG_COM_BACON.getCodigo());
                    lanche.setDescricao(X_EGG_COM_BACON.getDescricao());
                    lanche.setValor(calcularPrecoSemDesconto(solicitacaoLanche));
                    lanche.setValorComDesconto(calcularPrecoDesconto(solicitacaoLanche));
                    //TODO Adicionar os ingredientes do lanche no objeto e persistir em uma tabela relacional Lanche X Ingredientes
                    break;
                case X_PERSONALIZADO:
                    lanche.setTipoLanche(X_PERSONALIZADO.getCodigo());
                    lanche.setDescricao(X_PERSONALIZADO.getDescricao());
                    lanche.setValor(calcularPrecoSemDesconto(solicitacaoLanche));
                    lanche.setValorComDesconto(calcularPrecoDesconto(solicitacaoLanche));
                    //TODO Adicionar os ingredientes do lanche no objeto e persistir em uma tabela relacional Lanche X Ingredientes
                    break;
                default:
                    throw new IllegalAccessException("Tipo de lanche não cadastrado");
            }
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao criar o lanche", e);
        }

        return lancheRepository.add(lanche);
    }

    /**
     * Método que adequa o preço do lanche, seguindo promoções disponveis.
     *
     * @param solicitacaoLanche
     * @return BigDecimal
     */
    private BigDecimal calcularPrecoDesconto(SolicitacaoLanche solicitacaoLanche) {

        BigDecimal soma = new BigDecimal(0);
        List<Ingrediente> ingredientesLanche = new ArrayList<>();

        // Promoção Light
        if (solicitacaoLanche.getQtdAlface() > 0 && solicitacaoLanche.getQtdBacon() == 0) {
            ingredientesLanche = comporIngredientesLanche(solicitacaoLanche);
            soma = ingredientesLanche.stream().map(Ingrediente::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
            double desconto = soma.doubleValue() * 0.10;
            soma = BigDecimal.valueOf(soma.doubleValue() - desconto);

        } else {
            //Promoção Muita Carne
            aplicarBaconsGratis(solicitacaoLanche);
            aplicarHamburgueresGratis(solicitacaoLanche);

            //Promoção muito queijo
            aplicarQueijoGratis(solicitacaoLanche);

            ingredientesLanche = comporIngredientesLanche(solicitacaoLanche);
            soma = ingredientesLanche.stream().map(Ingrediente::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return soma;
    }

    /**
     * Método que calcula o preço sem aplicar descontos.
     *
     * @param solicitacaoLanche
     * @return BigDecimal
     */
    private BigDecimal calcularPrecoSemDesconto(SolicitacaoLanche solicitacaoLanche) {

        BigDecimal soma = new BigDecimal(0);
        List<Ingrediente> ingredientesLanche = new ArrayList<>();

        ingredientesLanche = comporIngredientesLanche(solicitacaoLanche);
        soma = ingredientesLanche.stream().map(Ingrediente::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        return soma;
    }

    private void aplicarBaconsGratis(final SolicitacaoLanche solicitacaoLanche) {
        int qtdBaconsDescontar = 0;
        for (int i = 0; i < solicitacaoLanche.getQtdBacon(); i++) {
            if (isMultiploDeTres(i)) {
                qtdBaconsDescontar++;
            }
        }
        solicitacaoLanche.setQtdBacon(solicitacaoLanche.getQtdBacon() - qtdBaconsDescontar);
    }

    private void aplicarHamburgueresGratis(final SolicitacaoLanche solicitacaoLanche) {
        int qtdHamburgueresDescontar = 0;
        for (int i = 0; i < solicitacaoLanche.getQtdHamburguer(); i++) {
            if (isMultiploDeTres(i)) {
                qtdHamburgueresDescontar++;
            }
        }
        solicitacaoLanche.setQtdHamburguer(solicitacaoLanche.getQtdHamburguer() - qtdHamburgueresDescontar);
    }

    private void aplicarQueijoGratis(final SolicitacaoLanche solicitacaoLanche) {
        int qtdQueijoDescontar = 0;
        for (int i = 0; i < solicitacaoLanche.getQtdQueijo(); i++) {
            if (isMultiploDeTres(i)) {
                qtdQueijoDescontar++;
            }
        }
        solicitacaoLanche.setQtdQueijo(solicitacaoLanche.getQtdQueijo() - qtdQueijoDescontar);
    }

    /**
     * Método utilizado para saber se a quantidade de itens selecionados é multiplo de três.
     *
     * @param quantidade
     * @return boolean
     */
    private boolean isMultiploDeTres(int quantidade) {
        if (quantidade != 0) {
            return quantidade % 3 == 0;
        }
        return false;
    }

    private List<Ingrediente> comporIngredientesLanche(SolicitacaoLanche solicitacaoLanche) {
        List<Ingrediente> listaIngredientes = new ArrayList<>();

        //Alface
        for (int a = 0; a < solicitacaoLanche.getQtdAlface(); a++) {
            listaIngredientes.add(new Ingrediente(new Long(TipoIngrediente.ALFACE.getCodigo()),
                            TipoIngrediente.ALFACE.getDescricao(), TipoIngrediente.ALFACE.getValor()));
        }

        // Bacon
        for (int b = 0; b < solicitacaoLanche.getQtdBacon(); b++) {
            listaIngredientes.add(new Ingrediente(new Long(TipoIngrediente.BACON.getCodigo()),
                            TipoIngrediente.BACON.getDescricao(), TipoIngrediente.BACON.getValor()));
        }

        // Hamburguer
        for (int h = 0; h < solicitacaoLanche.getQtdHamburguer(); h++) {
            listaIngredientes.add(new Ingrediente(new Long(TipoIngrediente.HAMBURGUER.getCodigo()),
                            TipoIngrediente.HAMBURGUER.getDescricao(), TipoIngrediente.HAMBURGUER.getValor()));
        }

        //Ovo
        for (int o = 0; o < solicitacaoLanche.getQtdOvo(); o++) {
            listaIngredientes.add(new Ingrediente(new Long(TipoIngrediente.OVO.getCodigo()), TipoIngrediente.OVO.getDescricao(),
                            TipoIngrediente.OVO.getValor()));
        }

        //Queijo
        for (int q = 0; q < solicitacaoLanche.getQtdQueijo(); q++) {
            listaIngredientes.add(new Ingrediente(new Long(TipoIngrediente.QUEIJO.getCodigo()),
                            TipoIngrediente.QUEIJO.getDescricao(),
                            TipoIngrediente.QUEIJO.getValor()));
        }
        return listaIngredientes;
    }
}
