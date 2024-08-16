package com.rodrigo_barbosa.series_filmes_api.domain.handler;

public class HandleTituloNaoEncontrado extends RuntimeException{


    public HandleTituloNaoEncontrado(String message){
        super(message);
    }
}
