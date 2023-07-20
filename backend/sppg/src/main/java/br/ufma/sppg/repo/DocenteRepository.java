package br.ufma.sppg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufma.sppg.dto.*;
import br.ufma.sppg.model.*;

public interface DocenteRepository 
    extends JpaRepository<Docente, Integer>{

    List<Docente> findByNome(String nome);
    
    Optional<Docente> findById(Integer idDocente);

    boolean existsById(Integer idDocente);

    @Query("SELECT d FROM Docente d")
    Optional<List<Docente>> obterDocentes();

    @Query("select p from Docente d join d.producoes p where (d.id = :idDocente and p.ano >= :anoIni and p.ano <= :anoFin)")
    Optional<List<Producao>> obterProducoes(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("select p from Docente d join d.producoes p where (d.id = :idDocente and p.tipo = 'P' and p.ano >= :anoIni and p.ano <= :anoFin)")
    Optional<List<Producao>> obterProdPeriodico(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("select p from Docente d join d.producoes p where (d.id = :idDocente and p.tipo = 'C' and p.ano >= :anoIni and p.ano <= :anoFin)")
    Optional<List<Producao>> obterProdCongresso(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("select o from Docente d join d.orientacoes o where (d.id = :idDocente and o.ano >= :anoIni and o.ano <= :anoFin)")
    List<Orientacao> obterOrientacoes(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);
//
    @Query("SELECT o FROM Docente d JOIN d.orientacoes o WHERE (d.id = :idDocente AND o.ano >= :anoIni AND o.ano <= :anoFin)")
    Optional<List<Orientacao>> obterOrientacaoDocenteData(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("SELECT o FROM Docente d JOIN d.orientacoes o WHERE (d.id = :idDocente)")
    Optional<List<Orientacao>> obterOrientacaoDocente(@Param("idDocente") Integer idDocente);
//
    @Query("SELECT p FROM Docente d JOIN d.producoes p WHERE (d.id = :idDocente AND p.ano >= :anoIni AND p.ano <= :anoFin)")
    Optional<List<Producao>> obterProducaoDocenteData(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("SELECT new br.ufma.sppg.dto.ProducoesDocDTO(p.id, p.ano, d.nome, p.titulo, p.nomeLocal, CASE WHEN p.orientacoes IS EMPTY THEN 'Não' ELSE 'Sim' END, p.qtdGrad, p.qtdMestrado ,p.qtdDoutorado) FROM Docente d JOIN d.producoes p WHERE (d.id = :idDocente)")
    Optional<List<ProducoesDocDTO>> obterProducaoDocente(@Param("idDocente") Integer idDocente);
//
    @Query("SELECT t FROM Docente d JOIN d.tecnicas t WHERE (d.id = :idDocente AND t.ano >= :anoIni AND t.ano <= :anoFin)")
    Optional<List<Tecnica>> obterTecnicaDocenteData(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

    @Query("SELECT new br.ufma.sppg.dto.TecnicasDocDTO(t.id, t.ano, d.nome, t.titulo, t.tipo,t.financiadora, CASE WHEN t.orientacoes IS EMPTY THEN 'Não' ELSE 'Sim' END, t.qtdGrad, t.qtdMestrado ,t.qtdDoutorado) FROM Docente d JOIN d.tecnicas t WHERE (d.id = :idDocente)")
    Optional<List<TecnicasDocDTO>> obterTecnicaDocente(@Param("idDocente") Integer idDocente);

    @Query("select t from Docente d join d.tecnicas t where (d.id = :idDocente and t.ano >= :anoIni and t.ano <= :anoFin)")
    List<Tecnica> obterTecnicas(@Param("idDocente") Integer idDocente, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);


}
