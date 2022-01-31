package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.controller.converter.AssociadoConverter;
import br.com.er.votacaoapi.helper.AssociadoHelper;
import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.service.AssociadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest {

    private static final String URL = "/associado";

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoConverter converter;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Cria um novo associado")
    void deveCriarUmNovoAssociado() throws Exception {

        Associado entityMock = AssociadoHelper.associadoMock();
        AssociadoDto dtoMock = AssociadoHelper.associadoDtoMock();

        when(this.converter.dtoToEntity(any(AssociadoDto.class))).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Associado.class))).thenReturn(dtoMock);
        when(this.service.novo(any(Associado.class))).thenReturn(entityMock);

        this.mvc.perform(post(URL)
                .content(getJsonPayload(dtoMock.getId(), dtoMock.getNome(), dtoMock.getCpf()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Retorna uma lista com todos os associados existentes.")
    void deveBuscarTodosOsAssociados() throws Exception {

        List<AssociadoDto> dtoMockList = AssociadoHelper.listAssociadoDtoMock();
        List<Associado> entityMockList = AssociadoHelper.listAssociadoMock();

        when(this.service.buscarTodos()).thenReturn(entityMockList);
        when(this.converter.listEntityToListDto(anyList())).thenReturn(dtoMockList);

        this.mvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Busca um associado pelo id.")
    void deveBuscarAssociadoPeloId() throws Exception {

        Associado entityMock = AssociadoHelper.associadoMock();
        AssociadoDto dtoMock = AssociadoHelper.associadoDtoMock();

        when(this.converter.dtoToEntity(any(AssociadoDto.class))).thenReturn(entityMock);
        when(this.service.buscar(anyLong())).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Associado.class))).thenReturn(dtoMock);

        this.mvc.perform(get(URL.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Edita um associado.")
    void deveEditarAssociado() throws Exception {

        Associado entityMock = AssociadoHelper.associadoMock();
        AssociadoDto dtoMock = AssociadoHelper.associadoDtoMock();

        when(this.converter.dtoToEntity(any(AssociadoDto.class))).thenReturn(entityMock);
        when(this.service.editar(anyLong(), any(Associado.class))).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Associado.class))).thenReturn(dtoMock);

        this.mvc.perform(put(URL.concat("/1"))
                .content(getJsonPayload(dtoMock.getId(), dtoMock.getNome(), dtoMock.getCpf()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Exclui um associado.")
    void deveExcluirUmAssociado() throws Exception {

        doNothing().when(this.service).excluir(anyLong());

        this.mvc.perform(delete(URL.concat("/1"))).andExpect(status().isNoContent());
    }

    String getJsonPayload(Long id, String nome, String cpf) throws JsonProcessingException {

        AssociadoDto associado = AssociadoDto.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(associado);
    }
}
