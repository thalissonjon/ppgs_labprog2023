package br.ufma.sppg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.sppg.dto.*;
import br.ufma.sppg.model.*;
import br.ufma.sppg.repo.*;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository repository;

    public List<Programa> obterPrograma(String nome) {
        verificarNome(nome);
        return repository.findByNome(nome);
    }

    //
    public List<Programa> obterProgramas(){
        List<Programa> programas = repository.obterProgramas().get();
        return programas;
    }

    public List<Docente> obterDocentesPrograma2(Integer idPrograma) {
        verificarId(idPrograma);
        return repository.obterDocentes(idPrograma).get();
    }

    //
    public IndicadoresDTO obterIndicadores(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        Double iRestrito = 0.0;
        Double iNRestrito = 0.0;
        Double iGeral = 0.0;
        List<Producao> producoes = repository.obterProducoes(idPrograma, anoIni, anoFin).get();

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

    //
    public GraficoDTO obterGrafico(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
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

        List<Producao> producoes = repository.obterProducoes(idPrograma, anoIni, anoFin).get();

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

    public List<DocenteQualisDTO> obterDocentesPrograma(Integer idPrograma){
        verificarId(idPrograma);
        List<Docente> docentesProd = repository.obterDocentes(idPrograma).get();
        List<DocenteQualisDTO> docentesQualis = new ArrayList<>();

        for(Docente docente : docentesProd){

            List<Producao> producoes = docente.getProducoes();
            DocenteQualisDTO docenteDTOAtual = new DocenteQualisDTO(docente.getId(), docente.getNome(), 0, 0, 0, 0, 0, 0, 0, 0);
            for(Producao prod : producoes){
                switch (prod.getQualis()) {
                case "A1":
                    docenteDTOAtual.setA1(docenteDTOAtual.getA1()+1);
                    break;
                case "A2":
                    docenteDTOAtual.setA2(docenteDTOAtual.getA2()+1);
                    break;
                case "A3":
                    docenteDTOAtual.setA3(docenteDTOAtual.getA3()+1);
                    break;
                case "A4":
                    docenteDTOAtual.setA4(docenteDTOAtual.getA4()+1);
                    break;
                case "B1":
                    docenteDTOAtual.setB1(docenteDTOAtual.getB1()+1);
                    break;
                case "B2":
                    docenteDTOAtual.setB1(docenteDTOAtual.getB2()+1);
                    break;
                case "B3":
                    docenteDTOAtual.setB1(docenteDTOAtual.getB3()+1);
                    break;
                case "B4":
                    docenteDTOAtual.setB1(docenteDTOAtual.getB4()+1);
                    break;
                default:
                    throw new ServicoRuntimeException("Uma das produções possui o Qualis inválido");
                }
            }
            docentesQualis.add(docenteDTOAtual);

        }

        return docentesQualis;

    }


    public Indice obterProducaoIndices(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        Double iRestrito = 0.0;
        Double iNRestrito = 0.0;
        Double iGeral = 0.0;
        List<Producao> producoes = new ArrayList<>();
        ArrayList<Integer> indicesProd = new ArrayList<>();

        for (Docente docente : docentes) {

            producoes = docente.getProducoes();

            for (Producao producao : producoes) {

                if (producao.getAno() >= anoIni && producao.getAno() <= anoFin
                        && !indicesProd.contains(producao.getId())) {

                    indicesProd.add(producao.getId());
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
            }
        }
        iGeral = iRestrito + iNRestrito;

        return new Indice(iRestrito, iNRestrito, iGeral);
    }

    // devolve uma List<Orientacao> de um dado programa dentro de um periodo
    public List<Orientacao> obterOrientacoes(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Orientacao> orientacoes = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as Orientacões de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId())) {

                    idOrientacoes.add(orientacao.getId());
                    orientacoes.add(orientacao);
                }
            }
        }

        return orientacoes;
    }

    // devolve uma List<Producao> de um dado programa dentro de um periodo
    public List<Producao> obterProducoes(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Producao> producoes = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        List<Producao> producoesDoc = new ArrayList<>();
        ArrayList<Integer> idProducoes = new ArrayList<>();

        // verifica as producões de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            producoesDoc = docente.getProducoes();
            for (Producao producao : producoesDoc) {

                if (producao.getAno() >= anoIni && producao.getAno() <= anoFin
                        && !idProducoes.contains(producao.getId())) {

                    idProducoes.add(producao.getId());
                    producoes.add(producao);
                }
            }
        }

        return producoes;
    }

    // devolve uma List<Tecnica> de um dado programa dentro de um periodo
    public List<Tecnica> obterTecnicas(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Tecnica> tecnicas = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        List<Tecnica> tecnicasDoc = new ArrayList<>();
        ArrayList<Integer> idTecnicas = new ArrayList<>();

        // verifica as tecnicas de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            tecnicasDoc = docente.getTecnicas();
            for (Tecnica tecnica : tecnicasDoc) {

                if (tecnica.getAno() >= anoIni && tecnica.getAno() <= anoFin && !idTecnicas.contains(tecnica.getId())) {

                    idTecnicas.add(tecnica.getId());
                    tecnicas.add(tecnica);
                }
            }
        }

        return tecnicas;
    }

    // conta quantas orientações possuem ao menos 1 producao e devolve quantas
    // cumprem essa meta
    public Integer quantitativoOrientacaoProducao(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as orientações de cada docente que pertence ao programa
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId()) && !orientacao.getProducoes().isEmpty()) {

                    idOrientacoes.add(orientacao.getId());
                }
            }
        }

        return idOrientacoes.size();
    }

    // conta quantas orientações possuem ao menos 1 tecnica e devolve quantas
    // cumprem essa meta
    public Integer quantitativoOrientacaoTecnica(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma).get();
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as orientações de cada docente que pertence ao programa
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId()) && !orientacao.getTecnicas().isEmpty()) {

                    idOrientacoes.add(orientacao.getId());
                }
            }
        }

        return idOrientacoes.size();
    }

    private void verificarNome(String nome) {
        if (nome == null) {
            throw new ServicoRuntimeException("Nome do Programa inválido");
        }
        if (nome.trim().equals("")) {
            throw new ServicoRuntimeException("Nome do Programa inválido");
        }
    }

    private void verificarId(Integer idPrograma) {
        verificarNumero(idPrograma);
        if (!repository.existsById(idPrograma)) {
            throw new ServicoRuntimeException("Id do Programa não está registrado");
        }
    }

    private void verificarData(Integer data1, Integer data2) {
        verificarNumero(data1);
        verificarNumero(data2);
        if (data1 > data2) {
            throw new ServicoRuntimeException("Data inical maior que a data final");
        }
    }

    private void verificarNumero(Integer numero) {
        if (numero == null) {
            throw new ServicoRuntimeException("Número Inválido");
        }

    }

}