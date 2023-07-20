package br.ufma.sppg.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.ufma.sppg.repo.*;

@SpringBootTest
@ActiveProfiles("test")
public class OrientacaoTest {

    @Autowired
    private OrientacaoRepository or;

    @Autowired
    private ProducaoRepository pr;

    @Autowired
    private TecnicaRepository tr;

    @Test
    public void deveSalvarOrientacao() {
        // criar um orientacao para salvar usando builder com todos os atributos
        // criar variavel data do tipo Date com a data de hoje

        Orientacao orientacao = Orientacao.builder().tipo("TCC").ano(2023).discente("Gabriel").titulo("TCC")
                .modalidade("Presencial").instituicao("UFMA").curso("Ciência da Computação").status("Ativo").build();

        // salvar o orientacao
        Orientacao orientacaoSalvo = or.save(orientacao);

        // rollback
        or.delete(orientacaoSalvo);

        // verificar se o orientacao foi salvo
        Assertions.assertNotNull(orientacaoSalvo);
        Assertions.assertEquals(orientacao.getTipo(), orientacaoSalvo.getTipo());
        Assertions.assertEquals(orientacao.getAno(), orientacaoSalvo.getAno());
        Assertions.assertEquals(orientacao.getDiscente(), orientacaoSalvo.getDiscente());
        Assertions.assertEquals(orientacao.getTitulo(), orientacaoSalvo.getTitulo());
        Assertions.assertEquals(orientacao.getModalidade(), orientacaoSalvo.getModalidade());
        Assertions.assertEquals(orientacao.getInstituicao(), orientacaoSalvo.getInstituicao());
        Assertions.assertEquals(orientacao.getCurso(), orientacaoSalvo.getCurso());
        Assertions.assertEquals(orientacao.getStatus(), orientacaoSalvo.getStatus());
    }

    @Test
    public void deveSalvarOrientacaoComProducoes() {

        Producao producao = Producao.builder().ano(2023).titulo("TCC").tipo("TCC").build();
        Producao producaoSalvo = pr.save(producao);
        ArrayList<Producao> producoes = new ArrayList<>();
        producoes.add(producaoSalvo);

        Orientacao orientacao = Orientacao.builder().tipo("TCC").ano(2023).discente("Gabriel").titulo("TCC")
                .modalidade("Presencial").instituicao("UFMA").curso("Ciência da Computação").status("Ativo")
                .producoes(producoes).build();

        Orientacao orientacaoSalvo = or.save(orientacao);

        // rollback
        or.delete(orientacaoSalvo);
        pr.delete(producaoSalvo);        

        Assertions.assertNotNull(orientacaoSalvo);
        Assertions.assertEquals(orientacao.getTipo(), orientacaoSalvo.getTipo());
        Assertions.assertEquals(orientacao.getAno(), orientacaoSalvo.getAno());
        Assertions.assertEquals(orientacao.getDiscente(), orientacaoSalvo.getDiscente());
        Assertions.assertEquals(orientacao.getTitulo(), orientacaoSalvo.getTitulo());
        Assertions.assertEquals(orientacao.getModalidade(), orientacaoSalvo.getModalidade());
        Assertions.assertEquals(orientacao.getInstituicao(), orientacaoSalvo.getInstituicao());
        Assertions.assertEquals(orientacao.getCurso(), orientacaoSalvo.getCurso());
        Assertions.assertEquals(orientacao.getStatus(), orientacaoSalvo.getStatus());
        Assertions.assertEquals(orientacao.getProducoes().get(0).getId(), orientacaoSalvo.getProducoes().get(0).getId());
    }

    @Test
    public void deveSalvarOrientacaoComTecnicas() {

        Tecnica tecnica = Tecnica.builder().titulo("Tecnica").ano(2023).build();
        Tecnica tecnicaSalvo = tr.save(tecnica);
        ArrayList<Tecnica> tecnicas = new ArrayList<>();
        tecnicas.add(tecnicaSalvo);

        Orientacao orientacao = Orientacao.builder().tipo("TCC").ano(2023).discente("Gabriel").titulo("TCC")
                .modalidade("Presencial").instituicao("UFMA").curso("Ciência da Computação").status("Ativo")
                .tecnicas(tecnicas).build();

        Orientacao orientacaoSalvo = or.save(orientacao);

        // rollback
        or.delete(orientacaoSalvo);
        tr.delete(tecnicaSalvo);
        
        Assertions.assertNotNull(orientacaoSalvo);
        Assertions.assertEquals(orientacao.getTipo(), orientacaoSalvo.getTipo());
        Assertions.assertEquals(orientacao.getAno(), orientacaoSalvo.getAno());
        Assertions.assertEquals(orientacao.getDiscente(), orientacaoSalvo.getDiscente());
        Assertions.assertEquals(orientacao.getTitulo(), orientacaoSalvo.getTitulo());
        Assertions.assertEquals(orientacao.getModalidade(), orientacaoSalvo.getModalidade());
        Assertions.assertEquals(orientacao.getInstituicao(), orientacaoSalvo.getInstituicao());
        Assertions.assertEquals(orientacao.getCurso(), orientacaoSalvo.getCurso());
        Assertions.assertEquals(orientacao.getStatus(), orientacaoSalvo.getStatus());
        Assertions.assertEquals(orientacao.getTecnicas().get(0).getId(), orientacaoSalvo.getTecnicas().get(0).getId());
    }

}
