package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.helper.AssociadoHelper;
import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AssociadoConverterTest {

    @InjectMocks
    private AssociadoConverter converter;

    @Test
    @DisplayName("Converte uma entidade para dto.")
    void deveConverterDeEntidadeParaDto() {

        Associado entityMock = AssociadoHelper.associadoMock();

        AssociadoDto dto = this.converter.entityToDto(entityMock);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    @Test
    @DisplayName("Converte um dto para entidade.")
    void deveConverterDeDtoParaEntidade() {

        AssociadoDto dtoMock = AssociadoHelper.associadoDtoMock();

        Associado entity = this.converter.dtoToEntity(dtoMock);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }

    @Test
    @DisplayName("Converte uma lista de entidades para uma lista de dtos.")
    void deveConverterUmaListaEntidadeParaUmaListaDto() {

        List<Associado> entityMockList = AssociadoHelper.listAssociadoMock();

        List<AssociadoDto> dtoMockList = this.converter.listEntityToListDto(entityMockList);

        assertNotNull(dtoMockList);
        assertEquals(1L, dtoMockList.get(0).getId());
    }

}
