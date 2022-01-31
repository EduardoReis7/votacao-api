package br.com.er.votacaoapi.business;

import br.com.er.votacaoapi.message.VoteResultsProducer;
import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.enums.StatusSessaoEnum;
import br.com.er.votacaoapi.model.enums.VotoEnum;
import br.com.er.votacaoapi.service.PautaService;
import br.com.er.votacaoapi.service.SessaoService;
import br.com.er.votacaoapi.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final VoteResultsProducer producer;

    public Sessao novo(Sessao sessao) {
        sessao.setPauta(buscarPauta(sessao.getIdPauta()));
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(tempoDaSessao(sessao.getDuracao())));
        sessao.setStatus(StatusSessaoEnum.ABERTA);
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
        sessao.setVotos(this.votoService.buscarTodosPorIdSessao(idSessao));
        return sessao;
    }

    public void contabilizarVotos(Long idSessao) {

        this.producer.sendMessage(idSessao, ResultadoVotacaoDto.builder()
                .votosAFavor(this.votoService.buscarTodosPorIdSessao(idSessao).stream()
                        .filter(voto -> voto.getVoto().equals(VotoEnum.SIM))
                        .count())
                .votosContra(this.votoService.buscarTodosPorIdSessao(idSessao).stream()
                        .filter(voto -> voto.getVoto().equals(VotoEnum.NAO))
                        .count())
                .total(this.votoService.buscarTodosPorIdSessao(idSessao).size())
                .build());
    }

    @Scheduled(fixedRate = 5000)
    private void scheduleVerificarSessao() {
        verificaSeSessaoEstaFechada();
    }

    private void verificaSeSessaoEstaFechada() {

        this.sessaoService.buscarTodasComStatusAberta().stream()
                .filter(sessao -> sessao.getDataFim().isBefore(LocalDateTime.now()))
                .forEach(sessao -> {
                    this.atualizarStatusSessao(sessao);
                    this.contabilizarVotos(sessao.getId());
                });
    }

    private void atualizarStatusSessao(Sessao sessao) {
        sessao.setStatus(StatusSessaoEnum.FECHADA);
        this.sessaoService.novo(sessao);
    }

    private Pauta buscarPauta(Long idPauta) {
        return this.pautaService.buscar(idPauta);
    }

    private Long tempoDaSessao(Long tempo) {
        return Optional.ofNullable(tempo).orElse(TEMPO_PADRAO_DE_SESSAO);
    }
}
