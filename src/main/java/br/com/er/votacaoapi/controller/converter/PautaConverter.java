package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.model.entity.Pauta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaConverter {

    public PautaDto entityToDto(Pauta entity) {
        return PautaDto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .build();
    }

    public Pauta dtoToEntity(PautaDto dto) {
        return Pauta.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .build();
    }

    public List<PautaDto> listEntityToListDto(List<Pauta> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
