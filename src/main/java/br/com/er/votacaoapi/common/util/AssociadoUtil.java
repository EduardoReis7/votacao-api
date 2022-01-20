package br.com.er.votacaoapi.common.util;

import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;

public class AssociadoUtil {

    public static AssociadoDto entityToDto(Associado entity) {
        return AssociadoDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .build();
    }

    public static Associado dtoToEntity(AssociadoDto dto) {
        return Associado.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .build();
    }
}
