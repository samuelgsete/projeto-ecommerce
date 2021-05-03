package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class HttpRequestMethodNotSupportedExceptionFilter {

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Erro> handle(HttpRequestMethodNotSupportedException ex) {
        var erro = new Erro(
            HttpStatus.BAD_REQUEST.toString(), 
            "O recurso solicitado não está criado", 
            ex.getMessage()
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }  
}