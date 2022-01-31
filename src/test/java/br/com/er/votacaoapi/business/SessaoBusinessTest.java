package br.com.er.votacaoapi.business;

import br.com.er.votacaoapi.helper.SessaoHelper;
import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.message.VoteResultsProducer;
import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.service.PautaService;
import br.com.er.votacaoapi.service.SessaoService;
import br.com.er.votacaoapi.service.VotoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoBusinessTest {

    @InjectMocks
    private SessaoBusiness business;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private VotoService votoService;

    @Mock
    private PautaService pautaService;

    @Mock
    private VoteResultsProducer producer;

    @Test
    @DisplayName("Cria uma nova sessão.")
    void deveCriarNovaSessao() {
        Sessao mock = SessaoHelper.sessaoMock();

        when(this.sessaoService.novo(any(Sessao.class))).thenReturn(mock);

        Sessao sessao = this.business.novo(mock);

        assertNotNull(sessao);
        assertEquals(1L, sessao.getId());
    }

    @Test
    @DisplayName("Busca uma sessão pelo id.")
    void deveBuscarUmaSessao() {
        Sessao mock = SessaoHelper.sessaoMock();

        when(this.sessaoService.buscar(anyLong())).thenReturn(mock);

        Sessao sessao = this.business.buscarSessao(1L);

        assertNotNull(sessao);
        assertEquals(1L, sessao.getId());
    }

    @Test
    @DisplayName("Retorna uma lista com todas as sessões.")
    void deveBuscarTodasAsSessoes() {

        List<Sessao> mockList = SessaoHelper.listSessaoMock();

        when(this.sessaoService.buscarTodos()).thenReturn(mockList);

        List<Sessao> sessoes = this.business.buscarTodasSessoes();

        assertNotNull(sessoes);
        assertEquals(2, sessoes.size());
    }

    @Test
    @DisplayName("Retorna uma sessão com todos os seus respectivos votos.")
    void deveDefinirOsVotosDaSessao() {

        Sessao mock = SessaoHelper.sessaoMock();
        List<Voto> mockList = VotoHelper.listVotoSimplificadoMock();

        when(this.sessaoService.buscar(anyLong())).thenReturn(mock);
        when(this.votoService.buscarTodosPorIdSessao(anyLong())).thenReturn(mockList);

        Sessao sessaoComVotos = this.business.definirVotosDaSessao(1L);

        assertNotNull(sessaoComVotos);
        assertEquals(2, sessaoComVotos.getVotos().size());
    }

    @Test
    @DisplayName("Realiza a contabilização dos votos e envia em uma mensagem para o Kafka.")
    void deveContabilizarOsVotosDaSessao() {

        doNothing().when(this.producer).sendMessage(anyLong(), any(ResultadoVotacaoDto.class));

        this.business.contabilizarVotos(1L);
        assertNotNull(this.producer);
    }
}
