package br.com.er.votacaoapi.helper;

import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import br.com.er.votacaoapi.model.dto.SessaoComVotosDto;
import br.com.er.votacaoapi.model.dto.SessaoInDto;
import br.com.er.votacaoapi.model.dto.SessaoOutDto;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.enums.StatusSessaoEnum;

import java.time.LocalDateTime;
import java.util.List;

public class SessaoHelper {

    public static Sessao sessaoMock() {
        return Sessao.builder()
                .id(1L)
                .duracao(null)
                .status(StatusSessaoEnum.ABERTA)
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusMinutes(1))
                .idPauta(1L)
                .pauta(PautaHelper.pautaMock())
                .votos(VotoHelper.listVotoSimplificadoMock())
                .build();
    }

    public static SessaoInDto sessaoInDtoMock() {
        return SessaoInDto.builder()
                .id(1L)
                .duracao(null)
                .idPauta(1L)
                .build();
    }

    public static SessaoOutDto sessaoOutDtoMock() {
        return SessaoOutDto.builder()
                .id(1L)
                .duracao(null)
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusMinutes(1))
                .build();
    }

    public static SessaoComVotosDto sessaoComVotosDtoMock() {
        return SessaoComVotosDto.builder()
                .id(1L)
                .duracao(null)
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusMinutes(1))
                .votos(VotoHelper.listVotoOutDtoMock())
                .build();
    }

    public static ResultadoVotacaoDto resultadoMock() {
        return ResultadoVotacaoDto.builder()
                .votosContra(0L)
                .votosAFavor(2L)
                .total(2)
                .build();
    }

    public static List<Sessao> listSessaoMock() {
        return List.of(sessaoMock(), sessaoMock());
    }

    public static List<SessaoOutDto> listSessaoOutDtoMock() {
        return List.of(sessaoOutDtoMock(), sessaoOutDtoMock());
    }
}
