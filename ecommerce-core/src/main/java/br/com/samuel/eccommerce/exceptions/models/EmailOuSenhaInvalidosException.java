package br.com.samuel.eccommerce.exceptions.models;

public class EmailOuSenhaInvalidosException extends Exception {
    
    public EmailOuSenhaInvalidosException(String mensagem) {
        super(mensagem);
    }
}