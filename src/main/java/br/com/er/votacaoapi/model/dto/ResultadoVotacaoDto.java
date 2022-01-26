package br.com.er.votacaoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDto {

    private Long votosAFavor;
    private Long votosContra;
    private Integer total;
}
