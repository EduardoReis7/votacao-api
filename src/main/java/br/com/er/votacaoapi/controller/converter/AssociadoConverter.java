package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociadoConverter {

    public AssociadoDto entityToDto(Associado entity) {
        return AssociadoDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .build();
    }

    public Associado dtoToEntity(AssociadoDto dto) {
        return Associado.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .build();
    }

    public List<AssociadoDto> listEntityToListDto(List<Associado> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
