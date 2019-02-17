package com.br.deliveryfast.domain.services;

import com.br.deliveryfast.domain.Ingrediente;
import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.TipoIngrediente;
import com.br.deliveryfast.domain.TipoLanche;
import com.br.deliveryfast.repository.IngredienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LancheService {

    private static final Logger LOG = Logger.getLogger(LancheService.class);

    @Autowired
    private IngredienteRepository ingredienteRepository;

    /**
     * Metodo responsavel por criar lanches de acordo com o tipo de lanche selecionado.
     *
     * @param tipoLanche
     * @return Lanche
     */
    public Lanche criarLanche(String tipoLanche) {
        Lanche lanche = new Lanche();
        long[] idIngredientes;
        try {
            switch (tipoLanche) {
                case TipoLanche.X_BACON:
                    idIngredientes = new long[]{2L, 3L, 5L};
                    lanche.setTipoLanche(TipoLanche.X_BACON);
                    lanche.setDescricao("Bacon, hamburguer de carne e queijo");
                    lanche.setIngredientes(ingredienteRepository.buscarIngredientes(idIngredientes));
                    lanche.setValor(calcularDescontoPromocao(lanche.getIngredientes()));
                    break;

                case TipoLanche.X_BURGUER:
                    idIngredientes = new long[]{3L, 5L};
                    lanche.setTipoLanche(TipoLanche.X_BACON);
                    lanche.setDescricao("Hamburguer de carne e queijo");
                    lanche.setIngredientes(ingredienteRepository.buscarIngredientes(idIngredientes));
                    lanche.setValor(calcularPreco(lanche));
                    break;

                case TipoLanche.X_EGG:
                    idIngredientes = new long[]{4L, 3L, 5L};
                    lanche.setTipoLanche(TipoLanche.X_EGG);
                    lanche.setDescricao("X-Egg', 'Ovo, hamburguer de carne e queijo");
                    lanche.setIngredientes(ingredienteRepository.buscarIngredientes(idIngredientes));
                    lanche.setValor(calcularPreco(lanche));
                    break;

                case TipoLanche.X_EGG_BACON:
                    idIngredientes = new long[]{4L, 2L, 3L, 5L};
                    lanche.setTipoLanche(TipoLanche.X_EGG_BACON);
                    lanche.setDescricao("Ovo, bacon, hamburguer de carne e queijo");
                    lanche.setIngredientes(ingredienteRepository.buscarIngredientes(idIngredientes));
                    lanche.setValor(calcularPreco(lanche));
                    break;
            }
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao criar o lanche", e);
        }

        return lanche;
    }

    /**
     * Método que cria lanche personalizado com a seleção de ingredientes escolhido pelo cliente.
     *
     * @param ingredientes
     * @return Lanche
     */
    public Lanche criarLanchePersonalizado(List<Ingrediente> ingredientes) {
        Lanche lanche = new Lanche();
        lanche.setTipoLanche(TipoLanche.X_PERSONALIZADO);
        lanche.setDescricao("Lanche personalizado");
        //lanche.setValor(calcularPreco(lanche));
        lanche.setValor(calcularDescontoPromocao(lanche.getIngredientes()));
        return lanche;
    }

    /**
     * Calcula valor dos ingredientes utilizados no lanche.
     *
     * @param lanche
     * @return BigDecimal
     */
    private BigDecimal calcularPreco(Lanche lanche) {
        Double soma = new Double(0);
        for (Ingrediente ingrediente : lanche.getIngredientes()) {
            soma += ingrediente.getValor().doubleValue();
        }

        calcularDescontoPromocao(lanche.getIngredientes());
        return new BigDecimal(soma);
    }

    /**
     * Método que adequa o preço do lanche, seguindo promoções disponveis.
     *
     * @param ingredientes
     * @return BigDecimal
     */
    private BigDecimal calcularDescontoPromocao(List<Ingrediente> ingredientes) {
        boolean contemAlface = false;
        int contadorCarne = 0;
        int contadorQueijo = 0;

        Double soma = new Double(0);
        Double descontoMuitaCarne = new Double(0);
        Double descontoMuitoQueijo = new Double(0);

        for (Ingrediente ingrediente : ingredientes) {
            soma += ingrediente.getValor().doubleValue();
            if (ingrediente.getId() == TipoIngrediente.ALFACE) {
                contemAlface = true;
            } else if (ingrediente.getId() == TipoIngrediente.HAMBUERGUER) {
                contadorCarne ++;
                if (isMultiploDeTres(contadorCarne)) {
                    descontoMuitaCarne += ingrediente.getValor().doubleValue();
                }

            } else if (ingrediente.getId() == TipoIngrediente.QUEIJO) {
                contadorQueijo++;
                if(isMultiploDeTres(contadorQueijo)){
                    descontoMuitoQueijo += ingrediente.getValor().doubleValue();
                }
            }
        }

        //Promocao Light
        if (contemAlface && contadorCarne == 0) {
            soma = soma - (soma.doubleValue() * 10) / 100;
        }

        //Promocao Muita Carne
        if (descontoMuitaCarne.doubleValue() > 0) {
            soma = soma - descontoMuitaCarne.doubleValue();
        }

        if (descontoMuitoQueijo.doubleValue() > 0) {
            soma = soma.doubleValue() - descontoMuitoQueijo.doubleValue();
        }

        return new BigDecimal(soma);
    }

    /**
     * Método utilizado para saber se a quantidade de itens selecionados é multiplo de três.
     *
     * @param quantidade
     * @return boolean
     */
    private boolean isMultiploDeTres(int quantidade) {
        if (quantidade % 3 == 0) {
            return true;
        }

        return false;
    }
}
