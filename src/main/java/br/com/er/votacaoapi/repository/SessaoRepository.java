package br.com.er.votacaoapi.repository;

import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.model.enums.StatusSessaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    List<Sessao> findAllByStatus(StatusSessaoEnum status);

}
