package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocenteQualisDTO {
    Integer id;
    String docente;
    Integer a1 = 0;
    Integer a2 = 0;
    Integer a3 = 0;
    Integer a4 = 0;
    Integer b1 = 0;
    Integer b2 = 0;
    Integer b3 = 0;
    Integer b4 = 0;
}
