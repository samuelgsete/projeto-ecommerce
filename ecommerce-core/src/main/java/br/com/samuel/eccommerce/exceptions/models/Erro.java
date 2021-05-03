package br.com.samuel.eccommerce.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Erro {

    private String codigo;
    private String mensagem;
    private String detalhes;
}