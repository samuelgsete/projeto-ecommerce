package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;
import br.com.samuel.ecommerce.exceptions.models.EstoqueVazioException;

@ControllerAdvice
public class EstoqueVazioExceptionFilter {
    
    @ExceptionHandler(value = { EstoqueVazioException.class })
    public ResponseEntity<EntityError> handle(EstoqueVazioException ex) {
        var erro = new EntityError(
            HttpStatus.BAD_REQUEST.toString(), 
            ex.getMessage()
        );
        return new ResponseEntity<EntityError>(erro, HttpStatus.BAD_REQUEST);
    }
}