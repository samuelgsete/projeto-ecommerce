package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;

@ControllerAdvice
public class HttpRequestMethodNotSupportedExceptionFilter {

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<EntityError> handle(HttpRequestMethodNotSupportedException ex) {
        var erro = new EntityError(
            HttpStatus.BAD_REQUEST.toString(), 
            "O recurso solicitado não está criado"
        );
        return new ResponseEntity<EntityError>(erro, HttpStatus.BAD_REQUEST);
    }  
}