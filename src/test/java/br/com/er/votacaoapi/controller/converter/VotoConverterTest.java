package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.helper.AssociadoHelper;
import br.com.er.votacaoapi.helper.VotoHelper;
import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.dto.VotoInDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.model.enums.VotoEnum;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoConverterTest {

    @InjectMocks
    private VotoConverter votoConverter;

    @Mock
    private AssociadoConverter associadoConverter;

    @Test
    @DisplayName("Converte de entidade para VotoOutDto.")
    void deveConverterDeEntityParaVotoOutDto() {

        AssociadoDto dtoMock = AssociadoHelper.associadoDtoMock();
        Voto entityMock = VotoHelper.votoMock();

        when(this.associadoConverter.entityToDto(any(Associado.class))).thenReturn(dtoMock);

        VotoOutDto votoOutDto = this.votoConverter.entityToVotoOutDto(entityMock);

        assertNotNull(votoOutDto);
        assertEquals(VotoEnum.SIM, votoOutDto.getVoto());
    }

    @Test
    @DisplayName("Converte de votoInDto para entidade.")
    void deveConverterDeVotoInDtoToEntidade() {

        VotoInDto dtoInMock = VotoHelper.votoInDtoMock();

        Voto entidade = this.votoConverter.votoInDtoToEntity(dtoInMock);

        assertNotNull(entidade);
        assertEquals(1L, entidade.getId());
    }

    @Test
    @DisplayName("Converte uma lista de entidades para uma lista de VotoOutDto.")
    void deveConverterUmaListaDeEntidadeParaUmaListaDeVotoOutDto() {

        List<Voto> entityMockList = VotoHelper.listVotoMock();

        List<VotoOutDto> votoOutDtos = this.votoConverter.listEntityToListDto(entityMockList);

        assertNotNull(votoOutDtos);
        assertEquals(VotoEnum.SIM, votoOutDtos.get(0).getVoto());
    }
}
