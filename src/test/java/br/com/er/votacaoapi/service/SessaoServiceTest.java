package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.helper.SessaoHelper;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.enums.StatusSessaoEnum;
import br.com.er.votacaoapi.repository.SessaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService service;

    @Mock
    private SessaoRepository repository;

    @Test
    @DisplayName("Cria uma nova sessão.")
    void deveCriarNovaSessao() {

        Sessao mock = SessaoHelper.sessaoMock();

        when(this.repository.save(any(Sessao.class))).thenReturn(mock);

        Sessao sessao = this.service.novo(mock);

        assertNotNull(sessao);
        assertEquals(1L, sessao.getId());
    }

    @Test
    @DisplayName("Retorna uma lista com todas as sessões.")
    void deveBuscarTodasAsSessoes() {

        List<Sessao> mockList = SessaoHelper.listSessaoMock();

        when(this.repository.findAll()).thenReturn(mockList);

        List<Sessao> sessoes = this.service.buscarTodos();

        assertNotNull(sessoes);
        assertEquals(2, sessoes.size());
    }

    @Test
    @DisplayName("Retorna uma lista com todas as sessões que estão abertas para votação.")
    void deveBuscarTodasSessoesComStatusAberta() {

        List<Sessao> mockList = SessaoHelper.listSessaoMock();

        when(this.repository.findAllByStatus(any(StatusSessaoEnum.class))).thenReturn(mockList);

        List<Sessao> abertas = this.service.buscarTodasComStatusAberta();

        assertNotNull(abertas);
        assertEquals(2, abertas.stream()
                .filter(sessao -> StatusSessaoEnum.ABERTA.equals(sessao.getStatus())).count());
    }

    @Test
    @DisplayName("Busca uma sessão com um id existente.")
    void deveBuscarUmaSessaoPorIdValido() {

        Sessao mock = SessaoHelper.sessaoMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(mock));

        Sessao sessao = this.service.buscar(1L);

        assertNotNull(sessao);
        assertEquals(1L, sessao.getId());
    }

    @Test
    @DisplayName("Joga uma exceção, pois não existe sessão para o id informado.")
    void deveBuscarUmaSessaoComIdInvalido() {

        when(this.repository.findById(anyLong())).thenThrow(NaoEncontradoException.class);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(4L));
    }

    @Test
    @DisplayName("Exclui todas as sessões existentes.")
    void deveExcluirTodasAsSessoes() {

        doNothing().when(this.repository).deleteAll();

        this.service.excluirTudo();
        List<Sessao> sessoes = this.service.buscarTodos();

        assertTrue(sessoes.isEmpty());
    }

}
