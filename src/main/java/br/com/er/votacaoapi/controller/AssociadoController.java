package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.controller.converter.AssociadoConverter;
import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/associado")
@Slf4j
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoConverter converter;

    @Operation(summary = "Cria um novo associado.")
    @PostMapping
    public ResponseEntity<AssociadoDto> novo(@RequestBody @Valid AssociadoDto associadoDto) {

        log.info("Novo associado criado.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.converter.entityToDto(this.service.novo(this.converter.dtoToEntity(associadoDto))));
    }

    @Operation(summary = "Retorna uma lista com todos associados.")
    @GetMapping
    public ResponseEntity<List<AssociadoDto>> buscarTodos() {

        return ResponseEntity.ok(this.converter.listEntityToListDto(this.service.buscarTodos()));
    }

    @Operation(summary = "Busca um associado pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> buscar(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.converter.entityToDto(this.service.buscarPorId(id)));
    }

    @Operation(summary = "Edita as informações de um associado.")
    @PutMapping("/{id}")
    public ResponseEntity<AssociadoDto> editar(@PathVariable("id") Long id, @RequestBody @Valid AssociadoDto associadoDto) {

        return ResponseEntity.ok(this.converter.entityToDto(this.service.editar(id, this.converter.dtoToEntity(associadoDto))));
    }

    @Operation(summary = "Exclui um associado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {

        this.service.excluir(id);
        log.info("Associado excluído. Id: {}.", id);
        return ResponseEntity.noContent().build();
    }
}
