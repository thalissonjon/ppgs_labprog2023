package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttEstDTO {
    Integer qtdGraduado;
    Integer qtdMestrado;
    Integer qtdDoutorado;
    Integer idProducao;
}