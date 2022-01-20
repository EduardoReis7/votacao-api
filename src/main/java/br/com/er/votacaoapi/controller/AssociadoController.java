package br.com.er.votacaoapi.controller;

import br.com.er.votacaoapi.common.util.AssociadoUtil;
import br.com.er.votacaoapi.model.dto.AssociadoDto;
import br.com.er.votacaoapi.model.entity.Associado;
import br.com.er.votacaoapi.service.AssociadoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociadoController.class);

    private final AssociadoService service;

    @PostMapping
    public ResponseEntity<Associado> novo(@RequestBody @Validated AssociadoDto associadoDto) {

        LOGGER.info("Novo associado criado!");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.service.novo(AssociadoUtil.dtoToEntity(associadoDto)));
    }
}
