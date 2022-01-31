package br.com.er.votacaoapi.helper;

import br.com.er.votacaoapi.model.dto.VotoInDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.model.enums.VotoEnum;

import java.util.List;

public class VotoHelper {

    public static Voto votoMock() {
        return Voto.builder()
                .id(1L)
                .voto(VotoEnum.SIM)
                .idSessao(1L)
                .sessao(SessaoHelper.sessaoMock())
                .idAssociado(1L)
                .associado(AssociadoHelper.associadoMock())
                .build();
    }

    public static VotoInDto votoInDtoMock() {
        return VotoInDto.builder()
                .id(1L)
                .idAssociado(1L)
                .idSessao(1L)
                .voto(VotoEnum.SIM)
                .build();
    }

    public static VotoOutDto votoOutDto() {
        return VotoOutDto.builder()
                .voto(VotoEnum.SIM)
                .associado(AssociadoHelper.associadoDtoMock())
                .build();
    }

    public static Voto votoSimplificado() {
        return Voto.builder()
                .id(1L)
                .voto(VotoEnum.SIM)
                .idSessao(1L)
                .idAssociado(1L)
                .build();
    }


    public static List<Voto> listVotoMock() {
        return List.of(votoMock(), votoMock());
    }

    public static List<Voto> listVotoSimplificadoMock() {
        return List.of(votoSimplificado(), votoSimplificado());
    }

    public static List<VotoOutDto> listVotoOutDtoMock() {
        return List.of(votoOutDto(), votoOutDto());
    }
}
