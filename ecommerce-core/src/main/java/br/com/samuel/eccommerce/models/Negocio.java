package br.com.samuel.eccommerce.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Negocio extends EntidadeBase {
    
    @Column
    @NotNull(message = "{nogocio.proprietario.notNull}")
    @NotBlank(message = "{negocio.priprietario.notBlank}")
    @Size(min = 2, max = 120, message = "{negocio.proprietario.size}")
    private String proprietario;

    @Column
    @NotNull(message = "{nogocio.nomeFantasia.notNull}")
    @NotBlank(message = "{negocio.nomeFantasia.notBlank}")
    @Size(min = 4, max = 60, message = "{negocio.nome.size}")
    private String nomeFantasia;

    @Column
    @NotNull(message = "{negocio.telefone.notNull}")
    @NotBlank(message = "{negocio.telefone.notBlank}")
    @Size(max = 11, message = "{negocio.telefone.size}")
    private String telefone;

    @Column
    @NotNull(message = "{negocio.email.notNull}")
    @NotBlank(message = "{negocio.email.notBlank}")
    @Size(max = 255, message = "{negocio.email.size}")
    @Email(message = "{negocio.email.valid}")
    private String email;

    @Column
    @NotNull(message = "{negocio.senha.notNull}")
    @NotBlank(message = "{negocio.senha.notBlank}")
    @Size(min = 4, max = 15, message = "{negocio.senha.size}")
    private String senha;

    @Column
    @NotNull(message = "{nogocio.descricao.notNull}")
    @NotBlank(message = "{negocio.descricao.notBlank}")
    @Size(min = 15, max = 512, message = "{negocio.descricao.size}")
    private String descricao;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @NotNull(message = "{negocio.endereco.notNull}")
    private Endereco endereco;
    
    @Column
    private double receita;

    @Column
    private Integer pedidosConcluidos;

    @Column
    private Integer pedidosCancelados;

    @Column
    private Integer totalClientes;

    @Column
    private Integer totalProdutosVendidos;
}