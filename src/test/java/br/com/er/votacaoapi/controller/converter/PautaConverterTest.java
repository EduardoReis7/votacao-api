package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.helper.PautaHelper;
import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PautaConverterTest {

    @InjectMocks
    private PautaConverter converter;

    @Test
    @DisplayName("Converte uma entidade para dto.")
    void deveConverterDeEntidadeParaDto() {

        Pauta entityMock = PautaHelper.pautaMock();

        PautaDto dto = this.converter.entityToDto(entityMock);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    @Test
    @DisplayName("Converte um dto para entidade.")
    void deveConverterDeDtoParaEntidade() {

        PautaDto dtoMock = PautaHelper.pautaDtoMock();

        Pauta entity = this.converter.dtoToEntity(dtoMock);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }

    @Test
    @DisplayName("Converte uma lista de entidades para uma lista de dtos.")
    void deveConverterUmaListaEntidadeParaListaDto() {

        List<Pauta> entityMockList = PautaHelper.listPautaMock();

        List<PautaDto> dtos = this.converter.listEntityToListDto(entityMockList);

        assertNotNull(dtos);
        assertEquals(1L, dtos.get(0).getId());
    }

}
