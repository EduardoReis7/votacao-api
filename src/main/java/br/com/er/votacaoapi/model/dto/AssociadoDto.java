package br.com.er.votacaoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDto {

    private Long id;
    private String nome;
    @CPF(message = "CPF inválido!")
    private String cpf;
}
