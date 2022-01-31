package br.com.er.votacaoapi.business;

import br.com.er.votacaoapi.exception.SessaoEncerradaException;
import br.com.er.votacaoapi.helper.AssociadoHelper;
import br.com.er.votacaoapi.helper.SessaoHelper;
import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.service.AssociadoService;
import br.com.er.votacaoapi.service.SessaoService;
import br.com.er.votacaoapi.service.VotoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoBusinessTest {

    @InjectMocks
    private VotoBusiness business;

    @Mock
    private VotoService votoService;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private AssociadoService associadoService;

    @Test
    @DisplayName("Cria novo voto.")
    void deveCriarVotoValido() {
        Voto votoMock = VotoHelper.votoMock();
        Sessao sessaoMock = SessaoHelper.sessaoMock();
        Associado associadoMock = AssociadoHelper.associadoMock();

        when(this.sessaoService.buscar(anyLong())).thenReturn(sessaoMock);
        when(this.associadoService.buscar(anyLong())).thenReturn(associadoMock);
        when(this.votoService.novo(any(Voto.class))).thenReturn(votoMock);

        Voto voto = this.business.novo(votoMock);

        assertNotNull(voto);
        assertEquals(1L, voto.getIdAssociado());
        assertEquals(1L, voto.getId());

    }

    @Test
    @DisplayName("Joga uma exceção, pois a sessão já foi encerrada.")
    void deveCriarVotoEmSessaoEncerrada() {
        Sessao sessaoMock = SessaoHelper.sessaoMock();
        sessaoMock.setDataFim(LocalDateTime.now().minusMinutes(3));
        Voto votoMock = VotoHelper.votoMock();

        when(this.sessaoService.buscar(anyLong())).thenReturn(sessaoMock);

        assertThrows(SessaoEncerradaException.class, () -> this.business.novo(votoMock));
    }

    @Test
    @DisplayName("Retorna uma lista com todos os votos de uma determinada sessão.")
    void deveBuscarVotosPorSessao() {
        List<Voto> mockList = VotoHelper.listVotoSimplificadoMock();

        when(this.votoService.buscarTodosPorIdSessao(anyLong())).thenReturn(mockList);

        List<Voto> votos = this.business.buscarVotosPorSessao(1L);

        assertNotNull(votos);
        assertEquals(2, votos.size());
    }

    @Test
    @DisplayName("Retorna uma lista com todos os votos existentes.")
    void deveBuscarTodosOsVotos() {
        List<Voto> mockList = VotoHelper.listVotoMock();

        when(this.votoService.buscarTodos()).thenReturn(mockList);

        List<Voto> votos = this.business.buscarTodosVotos();

        assertNotNull(votos);
        assertEquals(2, votos.size());
    }

    @Test
    @DisplayName("Busca um voto pelo id.")
    void deveBuscarVotoPorId() {
        Voto mock = VotoHelper.votoMock();

        when(this.votoService.buscar(anyLong())).thenReturn(mock);

        Voto voto = this.business.buscarVoto(1L);

        assertNotNull(voto);
        assertEquals(1L, voto.getId());
    }

}
