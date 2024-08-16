package com.rodrigo_barbosa.series_filmes_api.domain.handler;

public class HandleGeneroNaoEncontrado extends RuntimeException {

    public HandleGeneroNaoEncontrado(String message) {
        super(message);
    }
}
