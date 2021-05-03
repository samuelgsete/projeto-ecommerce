package br.com.samuel.eccommerce.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
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
public class Cliente extends EntidadeBase {
    
    @Column
    @NotNull(message = "{cliente.nome.notNull}")
    @NotBlank(message = "{cliente.nome.notBlank}")
    @Size(min = 2, max = 60, message = "{cliente.nome.size}")
    private String nome;
    
    @Column
    @NotNull(message = "{cliente.sobrenome.notNull}")
    @NotBlank(message = "{cliente.sobrenome.notBlank}")
    @Size(min = 2, max = 60, message = "{cliente.sobrenome.size}")
    private String sobrenome;

    @Column
    @NotNull(message = "{cliente.telefone.notNull}")
    @NotBlank(message = "{cliente.telefone.notBlank}")
    @Size(max = 11, message = "{cliente.telefone.size}")
    private String telefone;

    @Column
    @NotNull(message = "{cliente.email.notNull}")
    @NotBlank(message = "{cliente.email.notBlank}")
    @Size(max = 255, message = "{cliente.email.size}")
    @Email(message = "{cliente.email.valid}")
    private String email;

    @Column
    @NotNull(message = "{cliente.senha.notNull}")
    @NotBlank(message = "{cliente.senha.notBlank}")
    @Size(min = 4, max = 15, message = "{cliente.senha.size}")
    private String senha;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @NotNull(message = "{cliente.endereco.notNull}")
    private Endereco endereco;

    @Column
    private Integer totalPedidos;

    @Column
    private Integer totalConsumido;

    @Column 
    private double totalGasto;

    @Column
    private Integer negocioId;
}