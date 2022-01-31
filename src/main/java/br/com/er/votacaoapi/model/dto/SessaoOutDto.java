package br.com.er.votacaoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoOutDto {

    private Long id;
    private Long duracao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private PautaDto pauta;
}
