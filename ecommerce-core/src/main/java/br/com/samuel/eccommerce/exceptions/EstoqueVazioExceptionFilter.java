package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.Erro;
import br.com.samuel.eccommerce.exceptions.models.EstoqueVazioException;

@ControllerAdvice
public class EstoqueVazioExceptionFilter {
    
    @ExceptionHandler(value = { EstoqueVazioException.class })
    public ResponseEntity<Erro> handle(EstoqueVazioException ex) {
        var erro = new Erro(
            HttpStatus.BAD_REQUEST.toString(), 
            ex.getMessage(),
            "Uma ou mais produtos em seu pedido estão com estoque vázio"
        );
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }
}