package br.com.er.votacaoapi.exception.handler.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Error {

    private Integer status;
    private String message;
    private LocalDateTime dateTime;

}
