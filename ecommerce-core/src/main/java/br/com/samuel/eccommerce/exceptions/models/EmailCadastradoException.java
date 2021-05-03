package br.com.samuel.eccommerce.exceptions.models;

public class EmailCadastradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmailCadastradoException(String mensagem) {
        super(mensagem);
    }
}