package com.br.deliveryfast.domain.services;

import com.br.deliveryfast.domain.*;
import com.br.deliveryfast.repository.LancheRepository;
import com.br.deliveryfast.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Servico responsavel por gerenciar a criacao de pedidos.
 */
@Service
public class PedidoService {

    @Autowired
    private LancheRepository lancheRepository;
    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido adicionarPedido(Pedido pedido){
        if(pedido.getIdLanches() != null && pedido.getIdLanches().size() > 0){

        }
        return null;
    }

    @Transactional
    private Pedido adicionarPedidoComLanches(Pedido pedido){
        return null;
    }

    //    public void gerarPedido(Pedido pedido) {
    //        List<Lanche> lanches = new ArrayList<>();
    //        for (Cardapio itemSelecionado : pedido.getItensCardapio()) {
    //            if (itemSelecionado.getTipoLanche().equalsIgnoreCase(TipoLanche.X_PERSONALIZADO)) {
    //                lanches.add(lancheService.criarLanchePersonalizado(itemSelecionado.getAdicionais()));
    //            } else {
    //                lanches.add(lancheService.criarLanche(itemSelecionado.getTipoLanche()));
    //            }
    //        }
    //
    //        pedido.setValor(calcularPrecoPedido(lanches));
    //    }

    private BigDecimal calcularPrecoPedido(List<Lanche> lanches) {
        Double soma = new Double(0);
        for (Lanche lanche : lanches) {
            soma += lanche.getValor().doubleValue();
        }
        return new BigDecimal(soma);
    }
}
