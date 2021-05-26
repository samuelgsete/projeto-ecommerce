package br.com.samuel.ecommerce.exceptions.models;

public class EstoqueVazioException extends Exception {

    private static final long serialVersionUID = 1L;

    public EstoqueVazioException(String mensagem) {
        super(mensagem);
    }
}