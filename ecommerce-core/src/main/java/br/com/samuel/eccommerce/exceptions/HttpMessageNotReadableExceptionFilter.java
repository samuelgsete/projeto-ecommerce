package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class HttpMessageNotReadableExceptionFilter {

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<Erro> handle(HttpMessageNotReadableException ex) {
        var erro = new Erro(
            HttpStatus.BAD_REQUEST.toString(), 
            "Sua requisição está ilegível",
            ex.getMessage()
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }
}