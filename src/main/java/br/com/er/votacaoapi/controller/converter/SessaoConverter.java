package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.model.dto.SessaoDto;
import br.com.er.votacaoapi.model.entity.Sessao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoConverter {

    public SessaoDto entityToDto(Sessao entity) {
        return SessaoDto.builder()
                .id(entity.getId())
                .duracao(entity.getDuracao())
                .pauta(entity.getPauta())
                .build();
    }

    public Sessao dtoToEntity(SessaoDto dto) {
        return Sessao.builder()
                .id(dto.getId())
                .duracao(dto.getDuracao())
                .pauta(dto.getPauta())
                .build();
    }

    public List<SessaoDto> listEntityToListDto(List<Sessao> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
