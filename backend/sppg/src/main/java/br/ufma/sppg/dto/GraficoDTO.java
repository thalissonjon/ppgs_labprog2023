package br.ufma.sppg.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraficoDTO {
    List<Integer> ano;
    List<Integer> a1;
    List<Integer> a2;
    List<Integer> a3;
    List<Integer> a4;
}