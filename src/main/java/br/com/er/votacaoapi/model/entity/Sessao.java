package br.com.er.votacaoapi.model.entity;

import br.com.er.votacaoapi.model.enums.StatusSessaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long duracao;
    @Enumerated(value = EnumType.STRING)
    private StatusSessaoEnum status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Long idPauta;
    @OneToOne
    private Pauta pauta;
    @OneToMany
    @ToString.Exclude
    private List<Voto> votos;

}
