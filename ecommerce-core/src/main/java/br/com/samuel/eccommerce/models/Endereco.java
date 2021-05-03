package br.com.samuel.eccommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Endereco extends EntidadeBase {
    
    @Column
    @NotNull(message = "{endereco.rua.notNull}")
    @NotBlank(message = "{endereco.rua.notBlank}")
    @Size(max = 255, message = "{endereco.rua.size}")
    private String rua;

    @Column
    private Integer numero;

    @Column
    @NotNull(message = "{endereco.cep.notNull}")
    @NotBlank(message = "{endereco.cep.notBlank}")
    @Size(min = 8, max = 8, message = "{endereco.cep.size}")
    private String cep;

    @Column
    @NotNull(message = "{endereco.bairro.notNull}")
    @NotBlank(message = "{endereco.bairro.notBlank}")
    @Size(max = 60, message = "{endereco.bairro.size}")
    private String bairro;

    @Column
    @NotNull(message = "{endereco.municipio.notNull}")
    @NotBlank(message = "{endereco.municipio.notBlank}")
    @Size(max = 60, message = "{endereco.municipio.size}")
    private String municipio;
}