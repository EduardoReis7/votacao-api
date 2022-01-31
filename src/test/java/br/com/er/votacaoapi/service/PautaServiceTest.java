package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.helper.PautaHelper;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.repository.PautaRepository;
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
class PautaServiceTest {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Test
    @DisplayName("Cria uma nova pauta")
    void deveCriarUmaNovaPauta() {
        Pauta mock = PautaHelper.pautaMock();

        when(this.repository.save(any(Pauta.class))).thenReturn(mock);

        Pauta pauta = this.service.novo(mock);

        assertNotNull(pauta);
        assertEquals(1L, pauta.getId());
    }

    @Test
    @DisplayName("Retorna uma Lista com todas as pautas.")
    void deveBuscarTodasAsPautas() {
        List<Pauta> mockList = PautaHelper.listPautaMock();

        when(this.repository.findAll()).thenReturn(mockList);

        List<Pauta> pautas = this.service.buscarTodos();

        assertNotNull(pautas);
        assertEquals(2, pautas.size());
    }

    @Test
    @DisplayName("Busca uma pauta com um id existente.")
    void deveBuscarPautaComIdValido() {
        Pauta mock = PautaHelper.pautaMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(mock));

        Pauta pauta = this.service.buscar(1L);

        assertNotNull(pauta);
        assertEquals(1L, pauta.getId());
    }

    @Test
    @DisplayName("Joga uma exceção, pois não existe pauta para o id informado.")
    void deveBuscarPautaComIdInvalido() {

        when(this.repository.findById(anyLong())).thenThrow(NaoEncontradoException.class);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(4L));
    }

    @Test
    @DisplayName("Edita uma pauta.")
    void deveEditarUmaPauta() {

        Pauta mock = PautaHelper.pautaMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(mock));
        when(this.repository.save(any(Pauta.class))).thenReturn(mock);

        Pauta editado = this.service.editar(1L, mock);

        assertNotNull(editado);
        assertEquals("descricaoTeste", editado.getDescricao());
    }

    @Test
    @DisplayName("Exclui uma pauta.")
    void deveExcluirUmaPauta() {
        doNothing().when(this.repository).deleteById(anyLong());

        this.service.excluir(1L);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(1L));
    }

    @Test
    @DisplayName("Exclui todas as pautas existentes.")
    void devExcluirTodasAsPautas() {
        doNothing().when(this.repository).deleteAll();

        this.service.excluirTodos();
        List<Pauta> pautas = this.service.buscarTodos();

        assertTrue(pautas.isEmpty());
    }
}
