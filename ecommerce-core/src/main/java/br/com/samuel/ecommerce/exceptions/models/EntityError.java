package br.com.samuel.ecommerce.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EntityError {

    private String codigo;
    private String mensagem;
}