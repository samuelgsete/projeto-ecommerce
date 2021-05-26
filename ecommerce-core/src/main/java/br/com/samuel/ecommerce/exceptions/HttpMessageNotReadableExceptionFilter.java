package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;

@ControllerAdvice
public class HttpMessageNotReadableExceptionFilter {

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<EntityError> handle(HttpMessageNotReadableException ex) {
        var erro = new EntityError(
            HttpStatus.BAD_REQUEST.toString(), 
            "Sua requisição está ilegível"
        );
        return new ResponseEntity<EntityError>(erro, HttpStatus.BAD_REQUEST);
    }
}