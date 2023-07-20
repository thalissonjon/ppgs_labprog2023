package br.ufma.sppg.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.dto.*;
import br.ufma.sppg.model.*;
import br.ufma.sppg.service.*;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@RequestMapping("/api/programa")
public class ProgramaController {
    @Autowired
    ProgramaService programaService;
    
    
    @GetMapping("/obterProgramas") 
    public ResponseEntity<?> obterProgramas(){
        try{
            List<Programa> programas = programaService.obterProgramas();
            return new ResponseEntity<List<Programa>>(programas, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    @GetMapping("/indicadores/{idPrograma}/{anoInicial}/{anoFinal}") 
    public ResponseEntity<?> ObterIndicadores(@PathVariable("idPrograma") Integer idPrograma,
        @PathVariable("anoInicial") Integer anoIni, @PathVariable("anoFinal") Integer anoFin){
        
        try{
            IndicadoresDTO indicadores = programaService.obterIndicadores(idPrograma, anoIni, anoFin);
            return new ResponseEntity<IndicadoresDTO>(indicadores, HttpStatus.OK);
        }catch(ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_docentes/{idPrograma}")
    public ResponseEntity<?> obterDocentesPrograma(@PathVariable("idPrograma") Integer idPrograma){
        try{
            List <DocenteQualisDTO> docentes = programaService.obterDocentesPrograma(idPrograma);
            return new ResponseEntity<List <DocenteQualisDTO>>(docentes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/graficoProdvsQualis/{idPrograma}/{anoInicial}/{anoFinal}") 
    public ResponseEntity<?> ObterGrafico(@PathVariable("idPrograma") Integer idPrograma,
        @PathVariable("anoInicial") Integer anoIni, @PathVariable("anoFinal") Integer anoFin){
        
        try{
            GraficoDTO grafico = programaService.obterGrafico(idPrograma, anoIni, anoFin);
            return new ResponseEntity<GraficoDTO>(grafico, HttpStatus.OK);
        }catch(ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/obterPrograma")
    public ResponseEntity<?> obterPrograma(
            @RequestParam("programa") String nome){
        try{
            List <Programa> programas = programaService.obterPrograma(nome);
            return new ResponseEntity<List <Programa>>(programas, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/obterProducoesPrograma")
    public ResponseEntity<?> obterProducoesPrograma(
        @RequestParam("programa") Integer idPrograma, Integer anoIni, Integer anoFin){
        try{
            List <Producao> producoes = programaService.obterProducoes(idPrograma, anoIni, anoFin);
            return new ResponseEntity<List<Producao>>(producoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obterOrientacoesPrograma")
    public ResponseEntity<?> obterOrientacoesPorgrama(
        @RequestParam("programa") Integer idPrograma, Integer anoIni, Integer anoFin){
        try{
            List <Orientacao> orientacoes = programaService.obterOrientacoes(idPrograma, anoIni, anoFin);
            return new ResponseEntity<List<Orientacao>>(orientacoes, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obterTecnicasPrograma")
    public ResponseEntity<?> obterTecnicasPrograma(
        @RequestParam("programa") Integer idPrograma, Integer anoIni, Integer anoFin){
        try{
            List <Tecnica> tecnicas = programaService.obterTecnicas(idPrograma, anoIni, anoFin);
            return new ResponseEntity<List<Tecnica>>(tecnicas, HttpStatus.OK);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    ////////////////

    @GetMapping("/qtv_orientacoes_producao") // QTV = quantitativo
    public ResponseEntity<?> ObterQuantitativoOrientacaoProducao(
                        @RequestParam("idPrograma") Integer idPrograma,
                        @RequestParam("anoInicial") Integer anoIni,
                        @RequestParam("anoFinal") Integer aniFin){

        try{
            Integer quantitativo = programaService.quantitativoOrientacaoProducao(idPrograma, anoIni, aniFin);
            return new ResponseEntity<Integer>(quantitativo, HttpStatus.OK);

        }catch(ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/qtv_orientacoes_tecnica") // QTV = quantitativo
    public ResponseEntity<?> ObterQuantitativoOrientacaoTecnica(
                        @RequestParam("idPrograma") Integer idPrograma,
                        @RequestParam("anoInicial") Integer anoIni,
                        @RequestParam("anoFinal") Integer aniFin){

        try{
            Integer quantitativo = programaService.quantitativoOrientacaoTecnica(idPrograma, anoIni, aniFin);
            return new ResponseEntity<Integer>(quantitativo, HttpStatus.OK);
            
        }catch(ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
