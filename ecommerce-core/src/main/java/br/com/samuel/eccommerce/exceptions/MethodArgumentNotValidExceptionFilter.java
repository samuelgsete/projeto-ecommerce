package br.com.samuel.eccommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.samuel.eccommerce.exceptions.models.Erro;

@ControllerAdvice
public class MethodArgumentNotValidExceptionFilter  {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Erro> handle(MethodArgumentNotValidException ex) {
        final int INDICE_DO_PRIMEIRO_ERRO_DE_VALIDACAO_ENCONTRADO = 0;
        var primeiroErro = ex.getAllErrors().get(INDICE_DO_PRIMEIRO_ERRO_DE_VALIDACAO_ENCONTRADO);
        var mensagem = primeiroErro.getDefaultMessage();
        var erro = new Erro(HttpStatus.BAD_REQUEST.toString(), mensagem, ex.getMessage());
        return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
    }
}