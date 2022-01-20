package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.controller.converter.PautaConverter;
import br.com.er.votacaoapi.model.dto.PautaDto;
import br.com.er.votacaoapi.service.PautaService;
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

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/pauta")
public class PautaController {

    private final PautaService service;
    private final PautaConverter converter;

    @Operation(summary = "Cria uma nova pauta.")
    @PostMapping
    public ResponseEntity<PautaDto> novo(@RequestBody @Valid PautaDto pautaDto) {

        log.info("Nova pauta criada.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.converter.entityToDto(this.service.novo(this.converter.dtoToEntity(pautaDto))));
    }

    @Operation(summary = "Retorna uma lista de pautas.")
    @GetMapping
    public ResponseEntity<List<PautaDto>> buscarTodos() {
        return ResponseEntity.ok(this.converter.listEntityToListDto(this.service.buscarTodos()));
    }

    @Operation(summary = "Retorna uma pauta.")
    @GetMapping("/{id}")
    public ResponseEntity<PautaDto> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.converter.entityToDto(this.service.buscar(id)));
    }

    @Operation(summary = "Edita uma pauta.")
    @PutMapping("/{id}")
    public ResponseEntity<PautaDto> editar(@PathVariable("id") Long id, @RequestBody @Valid PautaDto pautaDto) {
        return ResponseEntity.ok(this.converter.entityToDto(this.service.editar(id, this.converter.dtoToEntity(pautaDto))));
    }

    @Operation(summary = "Exclui uma pauta.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        this.service.excluir(id);
        log.info("Pauta removida. Id: {}.", id);
        return ResponseEntity.noContent().build();
    }
}
