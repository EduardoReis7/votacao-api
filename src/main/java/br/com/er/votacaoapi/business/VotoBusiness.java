package br.com.er.votacaoapi.business;

import br.com.er.votacaoapi.exception.SessaoEncerradaException;
import br.com.er.votacaoapi.exception.VotoJaEfetuadoException;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.service.AssociadoService;
import br.com.er.votacaoapi.service.SessaoService;
import br.com.er.votacaoapi.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class VotoBusiness {

    private final VotoService votoService;
    private final SessaoService sessaoService;
    private final AssociadoService associadoService;

    public Voto novo(Voto voto) {
        voto.setSessao(sessaoService.buscar(voto.getIdSessao()));
        if (LocalDateTime.now().isAfter(voto.getSessao().getDataFim())) {
            throw new SessaoEncerradaException("A sessão de votação já foi encerrada.");
        }
        validaSeAssociadoJaVotou(voto);
        voto.setAssociado(associadoService.buscar(voto.getIdAssociado()));
        return this.votoService.novo(voto);
    }

    public List<Voto> buscarVotosPorSessao(Long idSessao) {
        return this.votoService.buscarPorIdSessao(idSessao);
    }

    public List<Voto> buscarTodosVotos() {
        return this.votoService.buscarTodos();
    }

    public Voto buscarVoto(Long id) {
        return this.votoService.buscar(id);
    }

    private void validaSeAssociadoJaVotou(Voto novoVoto) {
        List<Voto> votos = buscarVotosPorSessao(novoVoto.getIdSessao());
        if (!votos.isEmpty() && votos.stream().anyMatch(voto -> novoVoto.getIdAssociado().equals(voto.getIdAssociado()))) {
            throw new VotoJaEfetuadoException("O associado já votou nesta pauta.");
        }
    }

}
