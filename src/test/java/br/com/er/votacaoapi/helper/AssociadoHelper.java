package br.com.er.votacaoapi.helper;

import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;

import java.util.List;

public class AssociadoHelper {

    public static Associado associadoMock() {
        return Associado.builder()
                .id(1L)
                .nome("João")
                .cpf("36067540002")
                .build();
    }

    public static AssociadoDto associadoDtoMock() {
        return AssociadoDto.builder()
                .id(1L)
                .nome("João")
                .cpf("36067540002")
                .build();
    }

    public static List<Associado> listAssociadoMock() {
        return List.of(associadoMock(), associadoMock());
    }

    public static List<AssociadoDto> listAssociadoDtoMock() {
        return List.of(associadoDtoMock(), associadoDtoMock());
    }
}
