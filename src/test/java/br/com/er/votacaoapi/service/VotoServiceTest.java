package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.repository.VotoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService service;

    @Mock
    private VotoRepository repository;

    @Test
    @DisplayName("Cria novo voto")
    void deveCriarNovoVoto() {

        Voto mock = VotoHelper.votoMock();

        when(this.repository.save(any(Voto.class))).thenReturn(mock);

        Voto voto = this.service.novo(mock);

        assertNotNull(voto);
        assertEquals(1L, voto.getId());
    }

    @Test
    @DisplayName("Retorna uma lista com todos os votos.")
    void deveBuscarTodosOsVotos() {

        List<Voto> mockList = VotoHelper.listVotoMock();

        when(this.repository.findAll()).thenReturn(mockList);

        List<Voto> votos = this.service.buscarTodos();

        assertNotNull(votos);
        assertEquals(2, votos.size());
    }

    @Test
    @DisplayName("Retorna uma lista com todos os votos de uma determinada sessão.")
    void deveBuscarTodosVotosPorIdSessao() {

        List<Voto> mockList = VotoHelper.listVotoMock();

        when(this.repository.findAllByIdSessao(anyLong())).thenReturn(mockList);

        List<Voto> votos = this.service.buscarTodosPorIdSessao(1L);

        assertNotNull(votos);
        assertEquals(2, votos.size());
    }

    @Test
    @DisplayName("Busca uma sessão com um id existe.")
    void deveBuscarUmaSessaoPorIdValido() {

        Voto mock = VotoHelper.votoMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(mock));

        Voto voto = this.service.buscar(1L);

        assertNotNull(voto);
        assertEquals(1L, voto.getId());
    }
}
