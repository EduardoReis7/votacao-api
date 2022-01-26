package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.model.entity.Sessao;
import br.com.er.votacaoapi.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository repository;

    public Sessao novo(Sessao sessao) {
        return this.repository.save(sessao);
    }

    public List<Sessao> buscarTodos() {
        return this.repository.findAll();
    }

    public Sessao buscar(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Sessão não encontrada."));
    }

    public void excluirTudo() {
        this.repository.deleteAll();
    }
}
