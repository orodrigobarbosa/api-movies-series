package com.rodrigo_barbosa.series_filmes_api.domain.handler;

public class HandleAtorNaoEncontrado extends RuntimeException {
    public HandleAtorNaoEncontrado(String message) {
        super(message);
    }
}
