package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class EmailCadastradoExceptionFilter {

    @ExceptionHandler(value = { EmailCadastradoException.class })
    public ResponseEntity<Erro> handle(EmailCadastradoException ex) {
        var erro = new Erro(
            HttpStatus.BAD_REQUEST.toString(), 
            "O E-mail cadastrado já esta sendo utlizando",
            ex.getMessage()
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }
}