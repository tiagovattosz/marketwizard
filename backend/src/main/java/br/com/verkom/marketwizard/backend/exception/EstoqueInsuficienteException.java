package br.com.verkom.marketwizard.backend.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String message){
        super(message);
    }

}
