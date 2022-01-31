package br.com.er.votacaoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoComVotosDto {

    private Long id;
    private Long duracao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private PautaDto pauta;
    private List<VotoOutDto> votos;
}
