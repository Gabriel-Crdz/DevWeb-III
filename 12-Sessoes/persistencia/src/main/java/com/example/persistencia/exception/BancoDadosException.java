package com.example.persistencia.exception;

public class BancoDadosException extends RuntimeException{

    public BancoDadosException(String mensagem){
        super(mensagem);
    }
}
