package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.helper.PautaHelper;
import br.com.er.votacaoapi.helper.SessaoHelper;
import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.model.dto.SessaoComVotosDto;
import br.com.er.votacaoapi.model.dto.SessaoInDto;
import br.com.er.votacaoapi.model.dto.SessaoOutDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.model.entity.Sessao;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoConverterTest {

    @InjectMocks
    private SessaoConverter sessaoConverter;

    @Mock
    private VotoConverter votoConverter;

    @Mock
    private PautaConverter pautaConverter;

    @Test
    @DisplayName("Converte de entidade para SessaoOutDto.")
    void deveConverterDeEntidadeParaSessaoOutDto() {

        Sessao entityMock = SessaoHelper.sessaoMock();
        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        when(this.pautaConverter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);
        SessaoOutDto sessaoOutDto = this.sessaoConverter.entityToSessaoOutDto(entityMock);

        assertNotNull(sessaoOutDto);
        assertEquals(1L, sessaoOutDto.getId());
    }

    @Test
    @DisplayName("Converte de SessaoInDto para entidade.")
    void deveConverterDeSessaoInDtoParaEntidade() {

        SessaoInDto dtoInMock = SessaoHelper.sessaoInDtoMock();

        Sessao entidade = this.sessaoConverter.dtoToEntity(dtoInMock);

        assertNotNull(entidade);
        assertEquals(1L, entidade.getId());
    }

    @Test
    @DisplayName("Converte uma lista de entidade para uma lista de dtos.")
    void deveConverterUmaListaDeEntidadeParaUmaListaDto() {


        List<Sessao> entityMockList = SessaoHelper.listSessaoMock();
        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        when(this.pautaConverter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);
        List<SessaoOutDto> sessaoOutDtos = this.sessaoConverter.listEntityToListDto(entityMockList);

        assertNotNull(sessaoOutDtos);
        assertEquals(1L, sessaoOutDtos.get(0).getId());
    }

    @Test
    @DisplayName("Converte uma entidade para SessaoComVotos.")
    void deveConverterUmaEntidadeParaSessaoComVotos() {

        PautaDto dtoMock = PautaHelper.pautaDtoMock();
        List<VotoOutDto> outMockList = VotoHelper.listVotoOutDtoMock();
        Sessao entityMock = SessaoHelper.sessaoMock();

        when(this.pautaConverter.entityToDto(any(Pauta.class))).thenReturn(dtoMock);
        when(this.votoConverter.listEntityToListDto(anyList())).thenReturn(outMockList);

        SessaoComVotosDto sessaoComVotosDto = this.sessaoConverter.sessaoToSessaoComVotos(entityMock);

        assertNotNull(sessaoComVotosDto);
        assertEquals(1L, sessaoComVotosDto.getId());
    }
}
