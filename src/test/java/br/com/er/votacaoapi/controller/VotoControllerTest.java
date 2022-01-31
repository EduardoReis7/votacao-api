package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.business.VotoBusiness;
import br.com.er.votacaoapi.controller.converter.VotoConverter;
import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.model.dto.VotoInDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.model.enums.VotoEnum;
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

@WebMvcTest(VotoController.class)
class VotoControllerTest {

    private static final String URL = "/voto";

    @MockBean
    private VotoBusiness business;

    @MockBean
    private VotoConverter converter;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Cria um novo voto.")
    void deveCriarUmNovoVoto() throws Exception {

        VotoInDto dtoInMock = VotoHelper.votoInDtoMock();
        Voto entityMock = VotoHelper.votoMock();
        VotoOutDto dtoOutMock = VotoHelper.votoOutDto();

        when(this.converter.votoInDtoToEntity(any(VotoInDto.class))).thenReturn(entityMock);
        when(this.business.novo(any(Voto.class))).thenReturn(entityMock);
        when(this.converter.entityToVotoOutDto(any(Voto.class))).thenReturn(dtoOutMock);

        this.mvc.perform(post(URL)
                .content(getJsonPayload(dtoInMock.getId(), dtoInMock.getVoto(), dtoInMock.getIdSessao(), dtoInMock.getIdAssociado()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Retorna uma lista  com todos os votos existentes.")
    void deveBuscarTodosOsVotos() throws Exception {

        List<Voto> entityMockList = VotoHelper.listVotoMock();
        List<VotoOutDto> dtoOutMockList = VotoHelper.listVotoOutDtoMock();

        when(this.business.buscarTodosVotos()).thenReturn(entityMockList);
        when(this.converter.listEntityToListDto(anyList())).thenReturn(dtoOutMockList);

        this.mvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Busca um voto pelo id.")
    void deveBuscarUmVotoPeloId() throws Exception {

        Voto entityMock = VotoHelper.votoMock();
        VotoOutDto dtoOutMock = VotoHelper.votoOutDto();

        when(this.business.buscarVoto(anyLong())).thenReturn(entityMock);
        when(this.converter.entityToVotoOutDto(any(Voto.class))).thenReturn(dtoOutMock);

        this.mvc.perform(get(URL.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    String getJsonPayload(Long id, VotoEnum voto, Long idSessao, Long idAssociado) throws JsonProcessingException {

        VotoInDto votoInDto = VotoInDto.builder()
                .id(id)
                .voto(voto)
                .idSessao(idSessao)
                .idAssociado(idAssociado)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(votoInDto);
    }
}
