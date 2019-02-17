package com.br.deliveryfast;

import com.br.deliveryfast.controller.PedidoController;
import com.br.deliveryfast.domain.Cardapio;
import com.br.deliveryfast.domain.Pedido;
import com.br.deliveryfast.domain.TipoLanche;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeliveryfastApplicationTests {

	@Autowired
	private PedidoController pedidoController;

	@Test
	public void contextLoads() {
	}

//	public static final String X_BACON = "X-Bacon";
//	public static final String X_BURGUER = "X-Burguer";
//	public static final String X_EGG = "X-Egg";
//	public static final String X_EGG_BACON = "X-Egg Bacon";
//	public static final String X_PERSONALIZADO = "X_PERSONALIZADO";
	//TODO Valor dos lanches de cardápio, regra para cálculo de preço e promoções.
//	@Test
//	public void valorLancheXBacon(){
//		Pedido pedido = new Pedido();
//		pedido.setDtPedido(new Date());
//		Cardapio itemCardapioSelecionado = new Cardapio();
//		itemCardapioSelecionado.setTipoLanche(TipoLanche.X_BACON);
//
//		List<Cardapio> itensSelecionados = new ArrayList();
//		itensSelecionados.add(itemCardapioSelecionado);
//
//		pedido.setItensCardapio(itensSelecionados);
//		ResponseEntity respostaApi = pedidoController.criarPedido(pedido);
//		System.out.println(respostaApi);
//	}

}
