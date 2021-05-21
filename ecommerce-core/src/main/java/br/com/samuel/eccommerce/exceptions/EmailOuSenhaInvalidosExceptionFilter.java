package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.EmailOuSenhaInvalidosException;
import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class EmailOuSenhaInvalidosExceptionFilter {

    @ExceptionHandler(value = { EmailOuSenhaInvalidosException.class })
    public ResponseEntity<Erro> handle(EmailOuSenhaInvalidosException ex) {
        var erro = new Erro(
            HttpStatus.FORBIDDEN.toString(), 
            "E-mail ou Senha inválidos",
            "Não existe usuario com as credenciais informadas"
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.FORBIDDEN);
    }
}
