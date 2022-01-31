package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.helper.AssociadoHelper;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.repository.AssociadoRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService service;

    @Mock
    private AssociadoRepository repository;

    @Test
    @DisplayName("Cria um novo associado válido.")
    void deveCriarNovoAssociado() {
        Associado mock = AssociadoHelper.associadoMock();

        when(this.repository.save(any(Associado.class))).thenReturn(mock);

        Associado novoAssociado = this.service.novo(mock);
        assertEquals(novoAssociado.getId(), mock.getId());
        verify(this.repository).save(mock);
    }

    @Test
    @DisplayName("Retorna uma lista com todos os associados.")
    void deveListarTodosAssociados() {

        List<Associado> mockList = AssociadoHelper.listAssociadoMock();

        when(this.repository.findAll()).thenReturn(mockList);

        List<Associado> associados = this.service.buscarTodos();
        assertNotNull(associados);
        assertEquals(2, associados.size());
    }

    @Test
    @DisplayName("Busca um associado com um id existente.")
    void deveBuscarAssociadoComIdValido() {

        Associado mock = AssociadoHelper.associadoMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.ofNullable(mock));

        Associado associado = this.service.buscar(1L);

        assertNotNull(associado);
        assertEquals(1L, associado.getId());
    }


    @Test
    @DisplayName("Joga uma exceção, pois não existe associado para o id informado.")
    void deveBuscarAssociadoComIdInvalido() {

        when(this.repository.findById(anyLong())).thenThrow(NaoEncontradoException.class);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(4L));
    }

    @Test
    @DisplayName("Edita um associado.")
    void deveEditarAssociado() {

        Associado mock = AssociadoHelper.associadoMock();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(mock));
        when(this.repository.save(any(Associado.class))).thenReturn(mock);

        Associado editado = this.service.editar(1L, mock);
        assertNotNull(editado);
        assertEquals("36067540002", editado.getCpf());
    }

    @Test
    @DisplayName("Exclui um associado")
    void deveExcluirUmAssociado() {

        doNothing().when(this.repository).deleteById(anyLong());

        this.service.excluir(1L);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(1L));
    }

    @Test
    @DisplayName("Exclui todos os associados.")
    void deveExcluirTodosAssociados() {

        doNothing().when(this.repository).deleteAll();

        this.service.excluirTodos();
        List<Associado> associados = this.service.buscarTodos();

        assertTrue(associados.isEmpty());
    }
}
