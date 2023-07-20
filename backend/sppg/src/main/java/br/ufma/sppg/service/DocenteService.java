package br.ufma.sppg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.sppg.dto.*;
import br.ufma.sppg.model.*;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;
import jakarta.transaction.Transactional;

@Service
public class DocenteService {
    
    @Autowired
    DocenteRepository repository;

    public List<Docente> obterDocentes(){
        List<Docente> docentes = repository.obterDocentes().get();

        return docentes;
    }

    public IndicadoresDTO obterIndicadores(Integer idDocente, Integer anoIni,Integer anoFin){
        verificarId(idDocente);
        verificarData(anoIni, anoFin);
        Double iRestrito = 0.0;
        Double iNRestrito = 0.0;
        Double iGeral = 0.0;
        List<Producao> producoes = repository.obterProducoes(idDocente, anoIni, anoFin).get();

        for (Producao producao : producoes) {
            switch (producao.getQualis()) {
                case "A1":
                    iRestrito += 1.0f;
                    break;

                case "A2":
                    iRestrito += 0.85;
                    break;

                case "A3":
                    iRestrito += 0.725;
                    break;

                case "A4":
                    iRestrito += 0.625;
                    break;

                case "B1":
                    iNRestrito += 0.5;
                    break;

                case "B2":
                    iNRestrito += 0.25;
                    break;

                case "B3":
                    iNRestrito += 0.1;
                    break;

                case "B4":
                    iNRestrito += 0.05;
                    break;

                default:
                    throw new ServicoRuntimeException("Uma das produções possui o Qualis inválido");
            }
        }
        
        iGeral = iRestrito + iNRestrito;

        return new IndicadoresDTO(iGeral, iRestrito, iNRestrito, producoes.size());
    }

    @Transactional
    public List<Orientacao> obterOrientacaoDocenteData(Integer idDocente, Integer anoIni, Integer anoFin){
        List<Orientacao> orientacoes = repository.obterOrientacaoDocenteData(idDocente, anoIni, anoFin).get();

        return orientacoes;
    }

    @Transactional
    public List<Orientacao> obterOrientacaoDocente(Integer idDocente){
        List<Orientacao> orientacoes = repository.obterOrientacaoDocente(idDocente).get();

        return orientacoes;
    }

    @Transactional
    public List<Producao> obterProducaoDocenteData(Integer idDocente, Integer anoIni, Integer anoFin){
        List<Producao> producoes = repository.obterProducaoDocenteData(idDocente, anoIni, anoFin).get();

        return producoes;
    }

    @Transactional
    public List<ProducoesDocDTO> obterProducaoDocente(Integer idDocente){
        List<ProducoesDocDTO> producoes = repository.obterProducaoDocente(idDocente).get();

        return producoes;
    }
        
    @Transactional
    public List<Tecnica> obterTecnicaDocenteData(Integer idDocente, Integer anoIni, Integer anoFin){
        List<Tecnica> tecnicas = repository.obterTecnicaDocenteData(idDocente, anoIni, anoFin).get();

        return tecnicas;
    }
    
    @Transactional
    public List<TecnicasDocDTO> obterTecnicaDocente(Integer idDocente){
        List<TecnicasDocDTO> tecnicas = repository.obterTecnicaDocente(idDocente).get();

        return tecnicas;
    }

    public GraficoDTO obterGraficoPeriodico(Integer idDocente, Integer anoIni, Integer anoFin) {
        verificarId(idDocente);
        verificarData(anoIni, anoFin);

        List<Integer> anos = new ArrayList<>();
        List<Integer>[] a = new List[4];

        for (int i = 0; i < 4; i++) {
            a[i] = new ArrayList<>();
        }

        for (int ano = anoFin; ano >= anoIni; ano--) {
            anos.add(ano);
            for (int i = 0; i < 4; i++) {
                a[i].add(0);
            }
        }

        List<Producao> producoes = repository.obterProdPeriodico(idDocente, anoIni, anoFin).get();

        for (Producao producao : producoes) {
            int pos = anoFin - producao.getAno();
            switch (producao.getQualis()) {
                case "A1":
                    a[0].set(pos, a[0].get(pos) + 1);
                    break;

                case "A2":
                    a[1].set(pos, a[1].get(pos) + 1);
                    break;

                case "A3":
                    a[2].set(pos, a[2].get(pos) + 1);
                    break;

                case "A4":
                    a[3].set(pos, a[3].get(pos) + 1);
                    break;
            }
        }

        return new GraficoDTO(anos, a[0], a[1], a[2], a[3]);

    }

    public GraficoDTO obterGraficoCongresso(Integer idDocente, Integer anoIni, Integer anoFin) {
        verificarId(idDocente);
        verificarData(anoIni, anoFin);

        List<Integer> anos = new ArrayList<>();
        List<Integer>[] a = new List[4];

        for (int i = 0; i < 4; i++) {
            a[i] = new ArrayList<>();
        }

        for (int ano = anoFin; ano >= anoIni; ano--) {
            anos.add(ano);
            for (int i = 0; i < 4; i++) {
                a[i].add(0);
            }
        }

        List<Producao> producoes = repository.obterProdCongresso(idDocente, anoIni, anoFin).get();

        for (Producao producao : producoes) {
            int pos = anoFin - producao.getAno();
            switch (producao.getQualis()) {
                case "A1":
                    a[0].set(pos, a[0].get(pos) + 1);
                    break;

                case "A2":
                    a[1].set(pos, a[1].get(pos) + 1);
                    break;

                case "A3":
                    a[2].set(pos, a[2].get(pos) + 1);
                    break;

                case "A4":
                    a[3].set(pos, a[3].get(pos) + 1);
                    break;
            }
        }

        return new GraficoDTO(anos, a[0], a[1], a[2], a[3]);

    }

    public Indice obterIndice(Integer idDocente, Integer anoIni, Integer anoFin){ 
        verificarId(idDocente);
        verificarData(anoIni, anoFin);
        Double iRestrito = 0.0;
        Double iNRestrito = 0.0;
        Double iGeral = 0.0;
        List<Producao> producoes = new ArrayList<>();

        producoes = repository.obterProducoes(idDocente, anoIni, anoFin).get();

            for(Producao producao : producoes){
                    
                switch (producao.getQualis()) {
                    case "A1":
                        iRestrito += 1.0f;
                        break;
                            
                    case "A2":
                        iRestrito += 0.85;
                        break;

                    case "A3":
                        iRestrito += 0.725;
                        break;

                    case "A4":
                        iRestrito += 0.625;
                        break;

                    case "B1":
                        iNRestrito += 0.5;
                        break;

                    case "B2":
                        iNRestrito += 0.25;
                        break;

                    case "B3":
                        iNRestrito += 0.1;
                        break;
                        
                    case "B4":
                        iNRestrito += 0.05;
                        break;
                    
                    default:
                        throw new ServicoRuntimeException("Uma das produções possui o Qualis inválido");
                    }
            }

        iGeral = iRestrito + iNRestrito;

        return new Indice(iRestrito, iNRestrito, iGeral);
    }

    public List<Producao> obterProducoes(Integer idDocente, Integer anoIni, Integer anoFin){
        verificarId(idDocente);
        verificarData(anoIni, anoFin);

        return repository.obterProducoes(idDocente, anoIni, anoFin).get();

    }

    public List<Orientacao> obterOrientacoes(Integer idDocente, Integer anoIni, Integer anoFin){
        verificarId(idDocente);
        verificarData(anoIni, anoFin);

        return repository.obterOrientacoes(idDocente, anoIni, anoFin);

    }

    public List<Tecnica> obterTecnicas(Integer idDocente, Integer anoIni, Integer anoFin){
        verificarId(idDocente);
        verificarData(anoIni, anoFin);

        return repository.obterTecnicas(idDocente, anoIni, anoFin);

    }

    @Transactional
    public Docente salvarDocente(Docente doc){
        verificarDocente(doc);

        return repository.save(doc);
    }

    public Optional<Docente> obterDocente(Integer idDocente){
        verificarId(idDocente);

        return repository.findById(idDocente);
    }

    public List<Docente> obterDocentesNome(String nome){
        verificarPalavra(nome, "Nome inválido");

        return repository.findByNome(nome);
    }

    private void verificarPalavra(String nome, String mensagem){
        if(nome == null){
            throw new ServicoRuntimeException(mensagem);
        }
        if(nome.trim().equals("")){
            throw new ServicoRuntimeException(mensagem);
        }
    }

    private void verificarId(Integer idDocente){
        verificarNumero(idDocente, "Id Inválido");
        if(!repository.existsById(idDocente)){
            throw new ServicoRuntimeException("Id do Docente não está registrado");
        }
    }

    private void verificarData(Integer data1, Integer data2){
        verificarNumero(data1, "Data Inválida");
        verificarNumero(data2, "Data Inválida");
        if(data1 > data2){
            throw new ServicoRuntimeException("Data inical maior que a data final");
        }
    }

    private void verificarNumero(Integer numero, String mensagem){
        if(numero == null){
            throw new ServicoRuntimeException(mensagem);
        }

    }

    private void verificarDocente(Docente docente){
        verificarPalavra(docente.getNome(), "Nome inválido");
        verificarPalavra(docente.getLattes(), "Lattes inválido");
        verificarNumero(docente.getId(), "Id inválido");
        if(repository.existsById(docente.getId())){
            throw new ServicoRuntimeException("Id já registrado");
        }
        /*
        if(docente.getOrientacoes() == null){
            throw new ServicoRuntimeException("Lista de orientações inexistente");
        }
        if(docente.getTecnicas() == null){
            throw new ServicoRuntimeException("Lista de tecnicas inexistente");
        }
        if(docente.getProducoes() == null){
            throw new ServicoRuntimeException("Lista de produções inexistente");
        }
        if(docente.getProgramas() == null){
            throw new ServicoRuntimeException("Lista de programas inexistente");
        }
        */
    }
}