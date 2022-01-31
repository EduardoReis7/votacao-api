package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.model.entity.Voto;
import br.com.er.votacaoapi.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VotoService {

    private final VotoRepository repository;

    public Voto novo(Voto voto) {
        return this.repository.save(voto);
    }

    public List<Voto> buscarTodos() {
        return this.repository.findAll();
    }

    public Voto buscar(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Voto não encontrado"));
    }

    public List<Voto> buscarTodosPorIdSessao(Long idSessao) {
        return this.repository.findAllByIdSessao(idSessao);
    }
}
