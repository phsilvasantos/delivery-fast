package com.br.deliveryfast;

import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.domain.SolicitacaoLanche;
import com.br.deliveryfast.domain.services.LancheService;
import com.br.deliveryfast.domain.tipos.TipoIngrediente;
import com.br.deliveryfast.domain.tipos.TipoLanche;
import com.br.deliveryfast.repository.LancheRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por testes unitarios relacionados a entidade Lanche.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Ignore
public class LancheTests {

    @Autowired
    private LancheRepository lancheRepository;
    @Autowired
    private LancheService lancheService;

    @Test
    public void adicionarLanche() {
        Lanche lanche = new Lanche();
        lanche.setDescricao("Lanche de Teste");
        lanche.setTipoLanche(TipoLanche.X_BACON.getDescricao());
        lanche.setValor(new BigDecimal(10));
        lanche.setValorComDesconto(new BigDecimal(10));
        Lanche lancheSalvo = lancheRepository.add(lanche);
        Assert.assertTrue(lancheSalvo.getId() != null);
    }

    @Test
    public void obterLanchePorId() {
        Lanche lanche = new Lanche();
        lanche.setDescricao("Lanche de Teste");
        lanche.setTipoLanche(TipoLanche.X_BACON.getDescricao());
        lanche.setValor(new BigDecimal(10));
        lanche.setValorComDesconto(new BigDecimal(10));

        Lanche lancheSalvo = lancheRepository.add(lanche);
        Lanche lancheById = lancheRepository.getById(lancheSalvo.getId());

        Assert.assertTrue(lancheById != null);
    }

    @Test
    public void removerLanche() {
        Lanche lanche = new Lanche();
        lanche.setDescricao("Lanche de Teste Delete");
        lanche.setTipoLanche(TipoLanche.X_BACON.getDescricao());
        lanche.setValor(new BigDecimal(15));
        lanche.setValorComDesconto(new BigDecimal(15));

        Lanche lancheSalvo = lancheRepository.add(lanche);
        lancheRepository.delete(lancheSalvo);
        lancheSalvo = lancheRepository.getById(lancheSalvo.getId());

        Assert.assertTrue(lancheSalvo == null);
    }

    @Test
    public void validarValorItemCardapioXBacon() {
        //Composicao lanche :Bacon, hamburguer e queijo
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheCardapio = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_BACON.getCodigo());
        solicitacaoLanche.setQtdBacon(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheCardapio.compareTo(lanche.getValor()) == 0
                        && valorLancheCardapio.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorItemCardapioXBurguer() {
        //Composicao lanche :Hamburguer e queijo
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheCardapio = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_BURGUER.getCodigo());
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheCardapio.compareTo(lanche.getValor()) == 0
                        && valorLancheCardapio.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorItemCardapioXEgg() {
        //Composicao lanche Ovo, Hamburguer e queijo
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheCardapio = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_EGG.getCodigo());
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheCardapio.compareTo(lanche.getValor()) == 0
                        && valorLancheCardapio.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorItemCardapioXEggBacon() {
        //Composicao lanche Ovo,Bacon, Hamburguer e Queijo
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheCardapio = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_EGG.getCodigo());
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdBacon(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheCardapio.compareTo(lanche.getValor()) == 0
                        && valorLancheCardapio.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorLanchePromocaoLight() {
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.ALFACE.getValor());
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheSemDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        double descontoLight = valorLancheSemDesconto.doubleValue() * 0.10;
        BigDecimal valorLancheComDesconto = BigDecimal.valueOf(valorLancheSemDesconto.doubleValue() - descontoLight);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_PERSONALIZADO.getCodigo());
        solicitacaoLanche.setQtdAlface(1);
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheComDesconto.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorLanchePromocaoMuitaCarneHamburguer() {
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheSemDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        valoresIngredientes.clear();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheComDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_PERSONALIZADO.getCodigo());
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdHamburguer(5);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheComDesconto.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorLanchePromocaoMuitaCarneBacon() {
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheSemDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        valoresIngredientes.clear();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheComDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_PERSONALIZADO.getCodigo());
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdBacon(5);
        solicitacaoLanche.setQtdQueijo(1);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheComDesconto.compareTo(lanche.getValorComDesconto()) == 0);
    }

    @Test
    public void validarValorLanchePromocaoMuitoQueijo() {
        List<BigDecimal> valoresIngredientes = new ArrayList<>();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheSemDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        valoresIngredientes.clear();
        valoresIngredientes.add(TipoIngrediente.OVO.getValor());
        valoresIngredientes.add(TipoIngrediente.BACON.getValor());
        valoresIngredientes.add(TipoIngrediente.HAMBURGUER.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());
        valoresIngredientes.add(TipoIngrediente.QUEIJO.getValor());

        BigDecimal valorLancheComDesconto = valoresIngredientes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        SolicitacaoLanche solicitacaoLanche = new SolicitacaoLanche();
        solicitacaoLanche.setCodigoLanche(TipoLanche.X_PERSONALIZADO.getCodigo());
        solicitacaoLanche.setQtdOvo(1);
        solicitacaoLanche.setQtdHamburguer(1);
        solicitacaoLanche.setQtdBacon(1);
        solicitacaoLanche.setQtdQueijo(4);
        Lanche lanche = lancheService.criarLanche(solicitacaoLanche);

        Assert.assertTrue(valorLancheComDesconto.compareTo(lanche.getValorComDesconto()) == 0);
    }
}
