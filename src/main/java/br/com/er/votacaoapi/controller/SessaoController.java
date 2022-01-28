package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.business.SessaoBusiness;
import br.com.er.votacaoapi.controller.converter.SessaoConverter;
import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import br.com.er.votacaoapi.model.dto.SessaoComVotosDto;
import br.com.er.votacaoapi.model.dto.SessaoInDto;
import br.com.er.votacaoapi.model.dto.SessaoOutDto;
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
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoBusiness business;
    private final SessaoConverter converter;

    @Operation(summary = "Cria uma nova sessão.")
    @PostMapping
    public ResponseEntity<SessaoOutDto> novo(@RequestBody @Valid SessaoInDto sessaoDto) {

        log.info("Nova sessão criada.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.converter.entityToSessaoOutDto(this.business.novo(this.converter.dtoToEntity(sessaoDto))));
    }

    @Operation(summary = "Retorna uma lista com todas sessões.")
    @GetMapping
    public ResponseEntity<List<SessaoOutDto>> buscarTodos() {
        return ResponseEntity.ok(this.converter.listEntityToListDto(this.business.buscarTodasSessoes()));
    }

    @Operation(summary = "Retorna um objeto Sessão contendo os votos.")
    @GetMapping("/{idSessao}/votos")
    public ResponseEntity<SessaoComVotosDto> buscarTodosVotosDaSessao(@PathVariable("idSessao") Long idSessao) {
        return ResponseEntity.ok(this.converter.sessaoToSessaoComVotos(this.business.definirVotosDaSessao(idSessao)));
    }

    @Operation(summary = "Busca uma sessão pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<SessaoOutDto> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.converter.entityToSessaoOutDto(this.business.buscarSessao(id)));
    }

//    @Operation(summary = "Retorna o resultado da votação de uma determinada sessão.")
//    @GetMapping("/{idSessao}/resultado")
//    public ResponseEntity<ResultadoVotacaoDto> resultadoVotacao(@PathVariable("idSessao") Long idSessao) {
//        return ResponseEntity.ok(this.business.contabilizarVotos(idSessao));
//    }

}
