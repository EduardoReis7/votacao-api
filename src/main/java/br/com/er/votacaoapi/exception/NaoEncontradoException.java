package br.com.er.votacaoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 5262535017061239261L;

    public NaoEncontradoException(String message) {
        super(message);
    }
}
