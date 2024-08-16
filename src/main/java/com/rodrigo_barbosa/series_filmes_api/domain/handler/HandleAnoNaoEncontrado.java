package com.rodrigo_barbosa.series_filmes_api.domain.handler;

public class HandleAnoNaoEncontrado extends RuntimeException{

    public HandleAnoNaoEncontrado(String message){
        super(message);
    }

}
