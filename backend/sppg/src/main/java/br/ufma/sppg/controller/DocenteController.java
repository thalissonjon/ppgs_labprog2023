package br.ufma.sppg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.dto.*;
import br.ufma.sppg.model.*;
import br.ufma.sppg.service.*;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;


@RequestMapping("/api/docente")
@RestController
public class DocenteController{
    @Autowired
    TecnicaService tecnicaService;

    @Autowired
    ProducaoService producaoService;

    @Autowired
    OrientacaoService orientacaoServivce;

    @Autowired
    DocenteService docenteService;

    @GetMapping("/obterDocentes")
    public ResponseEntity<?> obterDocentesFiltro(){
        try{
            List<Docente> docentes = docenteService.obterDocentes();
            return new ResponseEntity<List<Docente>>(docentes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/indicadores/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterIndicadores(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
    
    try{
            IndicadoresDTO indicadores = docenteService.obterIndicadores(idDocente, anoIni, anoFin);
            return new ResponseEntity<IndicadoresDTO>(indicadores, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_orientacoesData/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterOrientacoesDocenteData(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
        try{
            List<Orientacao> orientacoes = docenteService.obterOrientacaoDocenteData(idDocente, anoIni, anoFin);
            return new ResponseEntity<List<Orientacao>>(orientacoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_orientacoes/{idDocente}")
    public ResponseEntity<?> obterOrientacoesDocente(@PathVariable("idDocente") Integer idDocente){
        try{
            List<Orientacao> orientacoes = docenteService.obterOrientacaoDocente(idDocente);
            return new ResponseEntity<List<Orientacao>>(orientacoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_producoesData/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterProducoesDocenteData(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
        try{
            List<Producao> producoes = docenteService.obterProducaoDocenteData(idDocente, anoIni, anoFin);
            return new ResponseEntity<List<Producao>>(producoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_producoes/{idDocente}")
    public ResponseEntity<?> obterProducoesDocente(@PathVariable("idDocente") Integer idDocente){
        try{
            List<ProducoesDocDTO> producoes = docenteService.obterProducaoDocente(idDocente);
            return new ResponseEntity<List<ProducoesDocDTO>>(producoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_tecnicasData/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterTecnicasDocenteData(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
        try{
            List<Tecnica> tecnicaDocente = docenteService.obterTecnicaDocenteData(idDocente, anoIni, anoFin);
            return new ResponseEntity<List<Tecnica>>(tecnicaDocente, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
        
    @GetMapping("/obter_tecnicas/{idDocente}")
    public ResponseEntity<?> obterTecnicasDeDocente(@PathVariable("idDocente") Integer idDocente){
        try{
            List<TecnicasDocDTO> tecnicaDocente = docenteService.obterTecnicaDocente(idDocente);
            return new ResponseEntity<List<TecnicasDocDTO>>(tecnicaDocente, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/graficoProdPervsQualis/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterGraficoPeriodico(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
        try{
            GraficoDTO grafico = docenteService.obterGraficoPeriodico(idDocente, anoIni, anoFin);
            return new ResponseEntity<GraficoDTO>(grafico, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/graficoProdCongvsQualis/{idDocente}/{anoIni}/{anoFin}")
    public ResponseEntity<?> obterGraficoCongresso(@PathVariable("idDocente") Integer idDocente,
    @PathVariable("anoIni") Integer anoIni, @PathVariable("anoFin") Integer anoFin){
        try{
            GraficoDTO grafico = docenteService.obterGraficoCongresso(idDocente, anoIni, anoFin);
            return new ResponseEntity<GraficoDTO>(grafico, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/addOrientacaoProd")
    public ResponseEntity<?> addOrientacaoProd(@RequestBody AddOriDTO id){
                
        try{
            Producao producao = producaoService.addOrientacoes(id.getId(), id.getIdProducao());
            return new ResponseEntity<Producao>(producao, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/attEstatisticasProd")
    public ResponseEntity<?> attEstatisticasProd(@RequestBody AttEstDTO id){    
        try{
            Producao producao = producaoService.attEstatisticas(id.getQtdGraduado(), id.getQtdMestrado(), id.getQtdDoutorado(), id.getIdProducao());
            return new ResponseEntity<Producao>(producao, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/addOrientacaoTec")
    public ResponseEntity<?> addOrientacaoTec(@RequestBody AddOriDTO id){
                
        try{
            Tecnica tecnica = tecnicaService.addOrientacoes(id.getId(), id.getIdProducao());
            return new ResponseEntity<Tecnica>(tecnica, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/attEstatisticasTec")
    public ResponseEntity<?> attEstatisticasTec(@RequestBody AttEstDTO id){    
        try{
            Tecnica tecnica = tecnicaService.attEstatisticas(id.getQtdGraduado(), id.getQtdMestrado(), id.getQtdDoutorado(), id.getIdProducao());
            return new ResponseEntity<Tecnica>(tecnica, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}