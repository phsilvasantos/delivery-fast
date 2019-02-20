package com.br.deliveryfast;

import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.Pedido;
import com.br.deliveryfast.repository.LancheRepository;
import com.br.deliveryfast.repository.PedidoRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Ignore
public class PedidoTests {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private LancheRepository lancheRepository;

    @Test
    public void listarPedidos() {
        List<Pedido> listaPedidos = pedidoRepository.listarTodos();

        //TODO elaborar validacao coerente
        Assert.assertTrue(listaPedidos.size() > 0);
    }

    @Test
    public void adicionarPedidoSemLanches() {
        Pedido pedido = new Pedido();
        pedido.setCliente("João");
        pedido.setEndereco("R. Hélio Marcelino, n 730. Próximo a escola");
        pedido.setStatus("REALIZADO");

        Pedido pedidoSalvo = pedidoRepository.add(pedido);
        Assert.assertTrue(pedido.getId() != null);
    }

    @Test
    public void adicionarPedidoComLanches() {
        Lanche lanche = new Lanche();
        lanche.setDescricao("Lanche de Teste - Pedido sem Lanche");
        lanche.setTipoLanche("X-Salada");

        Lanche lanche2 = new Lanche();
        lanche2.setDescricao("Lanche de Teste - Pedido sem Lanche");
        lanche2.setTipoLanche("X-Salada");

        lanche = lancheRepository.add(lanche);
        List<Long> idsLanche = new ArrayList<>();
        idsLanche.add(lanche.getId());

        lanche2 = lancheRepository.add(lanche2);
        idsLanche.add(lanche2.getId());

        Pedido pedido = new Pedido();
        pedido.setCliente("João");
        pedido.setEndereco("R. Hélio Marcelino, n 730. Próximo a escola");
        pedido.setStatus("REALIZADO");
        pedido.setIdLanches(idsLanche);

        Pedido pedidoSalvo = pedidoRepository.add(pedido);
        Assert.assertTrue(pedido.getId() != null && pedido.getIdLanches() != null && pedido.getIdLanches().size() > 0);
    }
}
