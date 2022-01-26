package br.com.er.votacaoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotoJaEfetuadoException extends RuntimeException {

    public VotoJaEfetuadoException(String message) {
        super(message);
    }

}
