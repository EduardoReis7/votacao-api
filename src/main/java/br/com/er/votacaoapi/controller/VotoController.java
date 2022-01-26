package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.business.VotoBusiness;
import br.com.er.votacaoapi.controller.converter.VotoConverter;
import br.com.er.votacaoapi.model.dto.VotoInDto;
import br.com.er.votacaoapi.model.dto.VotoOutDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/voto")
public class VotoController {

    private final VotoBusiness business;
    private final VotoConverter converter;

    @Operation(summary = "Cria um novo voto.")
    @PostMapping
    public ResponseEntity<VotoOutDto> novo(@RequestBody @Valid VotoInDto votoDto) {

        log.info("Novo voto criado.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.converter.entityToVotoOutDto(this.business.novo(this.converter.votoInDtoToEntity(votoDto))));
    }

    @Operation(summary = "Retorna uma lista de todos os votos.")
    @GetMapping
    public ResponseEntity<List<VotoOutDto>> buscarTodos() {

        return ResponseEntity.ok(this.converter.listEntityToListDto(this.business.buscarTodosVotos()));
    }

    @Operation(summary = "Busca um voto pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<VotoOutDto> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.converter.entityToVotoOutDto(this.business.buscarVoto(id)));
    }
}