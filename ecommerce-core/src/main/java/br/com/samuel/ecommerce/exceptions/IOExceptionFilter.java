package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;

import java.io.IOException;

@ControllerAdvice
public class IOExceptionFilter {
    
    @ExceptionHandler(value = { IOException.class })
    public ResponseEntity<EntityError> handle(IOException ex) {
        var erro = new EntityError(
                                HttpStatus.INSUFFICIENT_STORAGE.toString(),
                                ex.getMessage()
                            );
        return new ResponseEntity<EntityError>(erro, HttpStatus.INSUFFICIENT_STORAGE);
    }
}