package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;

@ControllerAdvice
public class MissingServletRequestParameterExceptionFilter {

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<EntityError> handle(MissingServletRequestParameterException ex) {
        var erro = new EntityError(
            HttpStatus.BAD_REQUEST.toString(), 
            "Faltam parâmetros na requisição"
        );
        return new ResponseEntity<EntityError>(erro, HttpStatus.BAD_REQUEST);
    }
}