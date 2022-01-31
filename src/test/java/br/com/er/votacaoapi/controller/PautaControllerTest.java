package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.controller.converter.PautaConverter;
import br.com.er.votacaoapi.helper.PautaHelper;
import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.service.PautaService;
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

@WebMvcTest(PautaController.class)
class PautaControllerTest {

    private static final String URL = "/pauta";

    @MockBean
    private PautaService service;

    @MockBean
    private PautaConverter converter;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Cria uma nova pauta.")
    void deveCriarUmaNovaPauta() throws Exception {

        Pauta entityMock = PautaHelper.pautaMock();
        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        when(this.converter.dtoToEntity(any(PautaDto.class))).thenReturn(entityMock);
        when(this.service.novo(any(Pauta.class))).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);

        this.mvc.perform(post(URL)
                .content(getJsonPayload(dtoMock.getId(), dtoMock.getTitulo(), dtoMock.getDescricao()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Retorna uma lista com todas as pautas existentes.")
    void deveBuscarTodasAsPautas() throws Exception {

        List<Pauta> entityMockList = PautaHelper.listPautaMock();
        List<PautaDto> dtoMockList = PautaHelper.listPautaDtoMock();

        when(this.service.buscarTodos()).thenReturn(entityMockList);
        when(this.converter.listEntityToListDto(anyList())).thenReturn(dtoMockList);

        this.mvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Busca uma pauta pelo id.")
    void deveBuscarUmaPautaPeloId() throws Exception {

        Pauta entityMock = PautaHelper.pautaMock();
        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        when(this.service.buscar(anyLong())).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);

        this.mvc.perform(get(URL.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Edita uma pauta.")
    void deveEditarUmaPauta() throws Exception {

        Pauta entityMock = PautaHelper.pautaMock();
        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        when(this.converter.dtoToEntity(any(PautaDto.class))).thenReturn(entityMock);
        when(this.service.editar(anyLong(), any(Pauta.class))).thenReturn(entityMock);
        when(this.converter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);

        this.mvc.perform(put(URL.concat("/1"))
                .content(getJsonPayload(dtoMock.getId(), dtoMock.getTitulo(), dtoMock.getDescricao()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Exclui uma pauta.")
    void deveExcluirUmaPauta() throws Exception {

        doNothing().when(this.service).excluir(anyLong());

        this.mvc.perform(delete(URL.concat("/1"))).andExpect(status().isNoContent());
    }

    String getJsonPayload(Long id, String titulo, String descricao) throws JsonProcessingException {

        Pauta pauta = Pauta.builder()
                .id(id)
                .titulo(titulo)
                .descricao(descricao)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pauta);
    }
}
