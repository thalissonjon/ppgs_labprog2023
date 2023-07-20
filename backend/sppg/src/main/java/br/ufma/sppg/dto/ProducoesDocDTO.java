package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducoesDocDTO {
    Integer id;
    Integer ano;
    String docente;
    String titulo;
    String local;
    String orientacao;
    Integer qtdGraduado;
    Integer qtdMestrado;
    Integer qtdDoutorado;
}
