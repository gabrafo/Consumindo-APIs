package dev.gabrafo.projeto.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GeneralExceptionHandler {
    // Tratamento da resposta enviada pelo servidor ao usuário
    @ExceptionHandler(InvalidEntryException.class)
    public ResponseEntity<StandardError> invalidEntry(InvalidEntryException e, HttpServletRequest request) {
        String error = "Erro de entrada";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<StandardError> client(ClientException e, HttpServletRequest request) {
        String error = "Erro no cliente";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
