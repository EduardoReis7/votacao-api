package br.com.er.votacaoapi.model.dto;

import br.com.er.votacaoapi.model.enums.VotoEnum;
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
public class VotoOutDto {

    private VotoEnum voto;
    private AssociadoDto associado;

}
