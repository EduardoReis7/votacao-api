package br.com.er.votacaoapi.controller.converter;

import br.com.er.votacaoapi.model.dto.SessaoComVotosDto;
import br.com.er.votacaoapi.model.dto.SessaoInDto;
import br.com.er.votacaoapi.model.dto.SessaoOutDto;
import br.com.er.votacaoapi.model.entity.Sessao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SessaoConverter {

    private final PautaConverter pautaConverter;
    private final VotoConverter votoConverter;

    public SessaoOutDto entityToSessaoOutDto(Sessao entity) {
        return SessaoOutDto.builder()
                .id(entity.getId())
                .duracao(entity.getDuracao())
                .dataInicio(entity.getDataInicio())
                .dataFim(entity.getDataFim())
                .pauta(this.pautaConverter.entityToDto(entity.getPauta()))
                .build();
    }

    public Sessao dtoToEntity(SessaoInDto dto) {
        return Sessao.builder()
                .id(dto.getId())
                .idPauta(dto.getIdPauta())
                .duracao(dto.getDuracao())
                .build();
    }
    public List<SessaoOutDto> listEntityToListDto(List<Sessao> entities) {
        return entities.stream().map(this::entityToSessaoOutDto).collect(Collectors.toList());
    }

    public SessaoComVotosDto sessaoToSessaoComVotos(Sessao sessao) {
        return SessaoComVotosDto.builder()
                .id(sessao.getId())
                .dataInicio(sessao.getDataInicio())
                .dataFim(sessao.getDataFim())
                .pauta(this.pautaConverter.entityToDto(sessao.getPauta()))
                .votos(this.votoConverter.listEntityToListDto(sessao.getVotos()))
                .build();
    }

}
