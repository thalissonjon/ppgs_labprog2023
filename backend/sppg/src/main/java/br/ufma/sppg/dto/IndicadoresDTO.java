package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicadoresDTO {
    Double iGeral;
    Double iRestrito;
    Double iNRestrito;
    Integer producoes;

}
