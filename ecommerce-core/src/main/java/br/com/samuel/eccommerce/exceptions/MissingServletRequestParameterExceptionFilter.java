package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class MissingServletRequestParameterExceptionFilter {

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<Erro> handle(MissingServletRequestParameterException ex) {
        var erro = new Erro(
            HttpStatus.BAD_REQUEST.toString(), 
            "Faltam parâmetros na requisição", 
            ex.getMessage()
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }
}