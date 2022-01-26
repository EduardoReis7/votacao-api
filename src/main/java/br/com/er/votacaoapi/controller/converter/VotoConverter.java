package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.model.dto.VotoInDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import br.com.er.votacaoapi.model.entity.Voto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VotoConverter {

    private final AssociadoConverter associadoConverter;

    public VotoOutDto entityToVotoOutDto(Voto entity) {
        return VotoOutDto.builder()
                .voto(entity.getVoto())
                .associado(this.associadoConverter.entityToDto(entity.getAssociado()))
                .build();
    }

    public Voto votoInDtoToEntity(VotoInDto dto) {
        return Voto.builder()
                .id(dto.getId())
                .voto(dto.getVoto())
                .idSessao(dto.getIdSessao())
                .idAssociado(dto.getIdAssociado())
                .build();
    }

    public List<VotoOutDto> listEntityToListDto(List<Voto> entities) {
        return entities.stream().map(this::entityToVotoOutDto).collect(Collectors.toList());
    }

}
