package br.com.er.votacaoapi.service;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssociadoService {

    private final AssociadoRepository repository;

    public Associado novo(Associado entity) {

        return this.repository.save(entity);
    }

    public List<Associado> buscarTodos() {

        return this.repository.findAll();
    }

    public Associado buscarPorId(Long id) {

        Optional<Associado> optAssociado = this.repository.findById(id);
        return optAssociado.orElseThrow(() -> new NaoEncontradoException("Associado não encontrado."));
    }

    public Associado editar(Long id, Associado novo){

        Optional<Associado> optAssociado = this.repository.findById(id);
        buscarPorId(id);
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
