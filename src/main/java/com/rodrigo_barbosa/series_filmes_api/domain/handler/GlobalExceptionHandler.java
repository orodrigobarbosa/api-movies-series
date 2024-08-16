package com.rodrigo_barbosa.series_filmes_api.domain.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HandleTituloNaoEncontrado.class)
    public ResponseEntity<?> HandleTituloNaoEncontrado(HandleTituloNaoEncontrado titulo, WebRequest request) {
        return new ResponseEntity<>(titulo.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleAtorNaoEncontrado.class) //handle tanto para ator quanto apra atriz
    public ResponseEntity<?> HandleAtorNaoEncontrado(HandleAtorNaoEncontrado ator, WebRequest request) {
        return new ResponseEntity<>(ator.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleGeneroNaoEncontrado.class)
    public ResponseEntity<?> HandleGeneroNaoEncontrado(HandleGeneroNaoEncontrado gen, WebRequest request) {

        return new ResponseEntity<>(gen.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandleAnoNaoEncontrado.class)
    public ResponseEntity<?> handleAnoNaoEncontrado(HandleAnoNaoEncontrado ano, WebRequest request) {
        return new ResponseEntity<>(ano.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException entity, WebRequest request) {
        return new ResponseEntity<>(entity.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException runtime, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runtime.getMessage());
    }
}
