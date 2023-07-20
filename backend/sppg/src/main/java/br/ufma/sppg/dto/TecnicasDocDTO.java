package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TecnicasDocDTO {
    Integer id;
    Integer ano;
    String docente;
    String titulo;
    String tipo;
    String financiadora;
    String orientacao;
    Integer qtdGraduado;
    Integer qtdMestrado;
    Integer qtdDoutorado;
}
