package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.model.entity.Pauta;
import br.com.er.votacaoapi.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PautaService {

    private final PautaRepository repository;

    public Pauta novo(Pauta pauta) {
        return this.repository.save(pauta);
    }

    public List<Pauta> buscarTodos() {
        return this.repository.findAll();
    }

    public Pauta buscar(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Pauta não encontrada."));
    }

    public Pauta editar(Long id, Pauta novo) {
        buscar(id);
        novo.setId(id);
        return this.repository.save(novo);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void excluirTodos() {
        this.repository.deleteAll();
    }
}
