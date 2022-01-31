package br.com.er.votacaoapi.helper;

import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.model.entity.Pauta;

import java.util.List;

public class PautaHelper {

    public static Pauta pautaMock() {
        return Pauta.builder()
                .id(1L)
                .titulo("tituloTeste")
                .descricao("descricaoTeste")
                .build();
    }

    public static PautaDto pautaDtoMock() {
        return PautaDto.builder()
                .id(1L)
                .titulo("tituloTeste")
                .descricao("descricaoTeste")
                .build();
    }

    public static List<Pauta> listPautaMock() {
        return List.of(pautaMock(), pautaMock());
    }

    public static List<PautaDto> listPautaDtoMock() {
        return List.of(pautaDtoMock(), pautaDtoMock());
    }
}
