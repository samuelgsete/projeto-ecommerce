package br.com.samuel.ecommerce.models;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
}