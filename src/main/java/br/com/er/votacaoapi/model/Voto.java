package br.com.er.votacaoapi.model;

import br.com.er.votacaoapi.model.enums.VotoEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoto;
    private VotoEnum voto;
    @ManyToOne
    private Sessao sessao;
    @OneToOne
    private Associado associado;
}
