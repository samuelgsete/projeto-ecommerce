package br.com.samuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.ecommerce.exceptions.models.EntityError;

@ControllerAdvice
public class MethodArgumentNotValidExceptionFilter  {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<EntityError> handle(MethodArgumentNotValidException ex) {
        final int INDICE_DO_PRIMEIRO_ERRO_DE_VALIDACAO_ENCONTRADO = 0;
        var primeiroErro = ex.getAllErrors().get(INDICE_DO_PRIMEIRO_ERRO_DE_VALIDACAO_ENCONTRADO);
        var mensagem = primeiroErro.getDefaultMessage();
        var erro = new EntityError(HttpStatus.BAD_REQUEST.toString(), mensagem);
        return new ResponseEntity<EntityError>(erro, HttpStatus.BAD_REQUEST);
    }
}