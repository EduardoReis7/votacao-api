package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.controller.converter.SessaoConverter;
import br.com.er.votacaoapi.model.dto.SessaoDto;
import br.com.er.votacaoapi.service.SessaoService;
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
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoService service;
    private final SessaoConverter converter;

    @Operation(summary = "Cria uma nova sessão.")
    @PostMapping
    public ResponseEntity<SessaoDto> novo(@RequestBody @Valid SessaoDto sessaoDto) {

        log.info("Nova sessao criada.");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.converter.entityToDto(this.service.novo(this.converter.dtoToEntity(sessaoDto))));
    }

    @Operation(summary = "Retorna uma lista com todas sessões.")
    @GetMapping
    public ResponseEntity<List<SessaoDto>> buscarTodos() {
        return ResponseEntity.ok(this.converter.listEntityToListDto(this.service.buscarTodos()));
    }

    @Operation(summary = "Busca uma sessão pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<SessaoDto> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.converter.entityToDto(this.service.buscar(id)));
    }

    @Operation(summary = "Edita as informações de uma sessão.")
    @PutMapping("/{id}")
    public ResponseEntity<SessaoDto> editar(@PathVariable("id") Long id, @RequestBody @Valid SessaoDto sessaoDto) {
        return ResponseEntity.ok(this.converter.entityToDto(this.service.editar(id, this.converter.dtoToEntity(sessaoDto))));
    }

    @Operation(summary = "Exclui uma sessão.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
