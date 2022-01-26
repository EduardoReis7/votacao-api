package br.com.er.votacaoapi.repository;

import br.com.er.votacaoapi.model.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findAllByIdSessao(Long idSessao);
}
