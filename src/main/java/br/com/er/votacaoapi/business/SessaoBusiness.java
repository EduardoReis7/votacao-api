package br.com.er.votacaoapi.business;

import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.enums.VotoEnum;
import br.com.er.votacaoapi.service.PautaService;
import br.com.er.votacaoapi.service.SessaoService;
import br.com.er.votacaoapi.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SessaoBusiness {

    private static final Long TEMPO_PADRAO_DE_SESSAO = 1L;

    private final SessaoService sessaoService;
    private final VotoService votoService;
    private final PautaService pautaService;

    public Sessao novo(Sessao sessao) {
        sessao.setPauta(buscarPauta(sessao.getIdPauta()));
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(tempoDaSessao(sessao.getDuracao())));
        return this.sessaoService.novo(sessao);
    }

    public Sessao buscarSessao(Long id) {
        return this.sessaoService.buscar(id);
    }

    public List<Sessao> buscarTodasSessoes() {
        return this.sessaoService.buscarTodos();
    }

    public Sessao definirVotosDaSessao(Long idSessao) {
        Sessao sessao = this.sessaoService.buscar(idSessao);
        sessao.setVotos(this.votoService.buscarPorIdSessao(idSessao));
        return sessao;
    }

    public ResultadoVotacaoDto contabilizarVotos(Long idSessao) {
        return ResultadoVotacaoDto.builder()
                .votosAFavor(this.votoService.buscarPorIdSessao(idSessao).stream()
                        .filter(voto -> voto.getVoto().equals(VotoEnum.SIM))
                        .count())
                .votosContra(this.votoService.buscarPorIdSessao(idSessao).stream()
                        .filter(voto -> voto.getVoto().equals(VotoEnum.NAO))
                        .count())
                .total(this.votoService.buscarTodos().size())
                .build();
    }

    private Pauta buscarPauta(Long idPauta) {
        return this.pautaService.buscar(idPauta);
    }

    private Long tempoDaSessao(Long tempo) {
        return Optional.ofNullable(tempo).orElse(TEMPO_PADRAO_DE_SESSAO);
    }
}
