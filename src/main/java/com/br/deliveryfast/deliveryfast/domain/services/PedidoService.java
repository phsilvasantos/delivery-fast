package com.br.deliveryfast.deliveryfast.domain.services;

import com.br.deliveryfast.deliveryfast.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private LancheService lancheService;

    public void gerarPedido(Pedido pedido) {
        List<Lanche> lanches = new ArrayList<>();
        for (Cardapio itemSelecionado : pedido.getItensCardapio()) {
            if (itemSelecionado.getTipoLanche().equalsIgnoreCase(TipoLanche.X_PERSONALIZADO)) {
                lanches.add(lancheService.criarLanchePersonalizado(itemSelecionado.getAdicionais()));
            } else {
                lanches.add(lancheService.criarLanche(itemSelecionado.getTipoLanche()));
            }
        }

        pedido.setValor(calcularPrecoPedido(lanches));
    }

    private BigDecimal calcularPrecoPedido(List<Lanche> lanches) {
        Double soma = new Double(0);
        for (Lanche lanche : lanches) {
            soma += lanche.getValor().doubleValue();
        }
        return new BigDecimal(soma);
    }
}
