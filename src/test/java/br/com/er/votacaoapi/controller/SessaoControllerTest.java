package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.business.SessaoBusiness;
import br.com.er.votacaoapi.controller.converter.SessaoConverter;
import br.com.er.votacaoapi.helper.SessaoHelper;
import br.com.er.votacaoapi.model.dto.SessaoComVotosDto;
import br.com.er.votacaoapi.model.dto.SessaoInDto;
import br.com.er.votacaoapi.model.dto.SessaoOutDto;
import br.com.er.votacaoapi.model.entity.Sessao;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
class SessaoControllerTest {

    private static final String URL = "/sessao";

    @MockBean
    private SessaoBusiness business;

    @MockBean
    private SessaoConverter converter;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Cria uma nova sessão.")
    void deveCriarNovaSessao() throws Exception {

        SessaoInDto dtoInMock = SessaoHelper.sessaoInDtoMock();
        Sessao entityMock = SessaoHelper.sessaoMock();
        SessaoOutDto dtoOutMock = SessaoHelper.sessaoOutDtoMock();

        when(this.converter.dtoToEntity(any(SessaoInDto.class))).thenReturn(entityMock);
        when(this.business.novo(any(Sessao.class))).thenReturn(entityMock);
        when(this.converter.entityToSessaoOutDto(any(Sessao.class))).thenReturn(dtoOutMock);

        this.mvc.perform(post(URL)
                .content(getJsonPayload(dtoInMock.getId(), dtoInMock.getDuracao(), dtoInMock.getIdPauta()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Retorna uma lista com todas as sessões existentes.")
    void deveBuscarTodasAsSessoes() throws Exception {

        List<Sessao> entityMockList = SessaoHelper.listSessaoMock();
        List<SessaoOutDto> dtoOutMockList = SessaoHelper.listSessaoOutDtoMock();

        when(this.business.buscarTodasSessoes()).thenReturn(entityMockList);
        when(this.converter.listEntityToListDto(anyList())).thenReturn(dtoOutMockList);

        this.mvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Retorna uma lista com todos os votos de uma determinada sessão.")
    void deveBuscarTodosOsVotosDaSessao() throws Exception {

        Sessao entityMock = SessaoHelper.sessaoMock();
        SessaoComVotosDto dtoComVotosMock = SessaoHelper.sessaoComVotosDtoMock();

        when(this.business.definirVotosDaSessao(anyLong())).thenReturn(entityMock);
        when(this.converter.sessaoToSessaoComVotos(entityMock)).thenReturn(dtoComVotosMock);

        this.mvc.perform(get(URL.concat("/1/votos"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Busca uma sessão.")
    void deveBuscarUmaSessao() throws Exception {

        Sessao entityMock = SessaoHelper.sessaoMock();
        SessaoOutDto dtoOutMock = SessaoHelper.sessaoOutDtoMock();

        when(this.business.buscarSessao(anyLong())).thenReturn(entityMock);
        when(this.converter.entityToSessaoOutDto(any(Sessao.class))).thenReturn(dtoOutMock);

        this.mvc.perform(get(URL.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    String getJsonPayload(Long id, Long duracao, Long idPauta) throws JsonProcessingException {

        SessaoInDto sessaoInDto = SessaoInDto.builder()
                .id(id)
                .duracao(duracao)
                .idPauta(idPauta)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(sessaoInDto);
    }
}
