package br.com.samuel.ecommerce.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
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
public class Usuario extends EntidadeBase {

    @Column
    @NotNull(message = "{usuario.nome.notNull}")
    @NotBlank(message = "{usuario.nome.notBlank}")
    @Size(min = 2, max = 60, message = "{usuario.nome.size}")
    private String nome;
    
    @Column
    @NotNull(message = "{cliente.sobrenome.notNull}")
    @NotBlank(message = "{cliente.sobrenome.notBlank}")
    @Size(min = 2, max = 60, message = "{usuario.sobrenome.size}")
    private String sobrenome;

    @Column
    @NotNull(message = "{usuario.telefone.notNull}")
    @NotBlank(message = "{usuario.telefone.notBlank}")
    @Size(max = 11, message = "{usuario.telefone.size}")
    private String telefone;
    
    @Column
    @NotNull(message = "{usuario.email.notNull}")
    @NotBlank(message = "{usuario.email.notBlank}")
    @Size(max = 255, message = "{usuario.email.size}")
    @Email(message = "{usuario.email.valid}")
    private String email;
    
    @Column
    @NotNull(message = "{usuario.senha.notNull}")
    @NotBlank(message = "{usuario.senha.notBlank}")
    @Size(min = 4, message = "{usuario.senha.size}")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="perfil_usuario",
            joinColumns=@JoinColumn(name = "usuario_id"),
            inverseJoinColumns=@JoinColumn(name="perfil_id")
    )
    private Set<Perfil> perfis = new HashSet<Perfil>();
  
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @NotNull(message = "{usuario.endereco.notNull}")
    private Endereco endereco;

    @Column
    private Boolean estaAtivo;

    @Column
    private String codigoVerificacao;
}