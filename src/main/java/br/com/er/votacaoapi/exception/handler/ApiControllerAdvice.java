package br.com.er.votacaoapi.exception.handler;

import br.com.er.votacaoapi.exception.NaoEncontradoException;
import br.com.er.votacaoapi.exception.SessaoEncerradaException;
import br.com.er.votacaoapi.exception.VotoJaEfetuadoException;
import br.com.er.votacaoapi.exception.handler.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NaoEncontradoException.class)
    public ResponseEntity<Error> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(Error.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .dateTime(LocalDateTime.now())
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = SessaoEncerradaException.class)
    public ResponseEntity<Error> handleSessaoEncerradaException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .dateTime(LocalDateTime.now())
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = VotoJaEfetuadoException.class)
    public ResponseEntity<Error> handleVotoJaEfetuadoException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .dateTime(LocalDateTime.now())
                        .message(ex.getLocalizedMessage())
                        .build()
                );
    }
}
