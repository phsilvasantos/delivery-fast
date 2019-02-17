package com.br.deliveryfast;

import com.br.deliveryfast.domain.Lanche;
import com.br.deliveryfast.repository.LancheRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancheTests {

    @Autowired
    private LancheRepository lancheRepository;

    @Test
    public void adicionarLanche(){
        Lanche lanche = new Lanche();
        lanche.setDescricao("Lanche de Teste");
        lanche.setTipoLanche("X-Bacon");

        Lanche lancheSalvo = lancheRepository.add(lanche);
        Assert.assertTrue(lancheSalvo.getId() != null);
    }
}
