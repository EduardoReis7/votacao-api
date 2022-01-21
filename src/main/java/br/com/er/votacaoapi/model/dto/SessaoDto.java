package br.com.er.votacaoapi.model.dto;

import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.model.entity.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDto {

    private Long id;
    private Long duracao;
    private Pauta pauta;
}
