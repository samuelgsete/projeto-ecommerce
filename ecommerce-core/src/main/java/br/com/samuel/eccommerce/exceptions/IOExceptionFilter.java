package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class IOExceptionFilter {
    
    @ExceptionHandler(value = { IOException.class })
    public ResponseEntity<Erro> handle(IOException ex) {
        var erro = new Erro(
                                HttpStatus.INSUFFICIENT_STORAGE.toString(), 
                                "Não foi possivel ler o arquivo",
                                ex.getMessage()
                            );
        return new ResponseEntity<Erro>(erro, HttpStatus.INSUFFICIENT_STORAGE);
    }
}