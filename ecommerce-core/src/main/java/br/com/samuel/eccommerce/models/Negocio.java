package br.com.samuel.eccommerce.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
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
    @NotNull(message = "{nogocio.nomeFantasia.notNull}")
    @NotBlank(message = "{negocio.nomeFantasia.notBlank}")
    @Size(min = 4, max = 60, message = "{negocio.nome.size}")
    private String nomeFantasia;

    @Column
    @NotNull(message = "{nogocio.descricao.notNull}")
    @NotBlank(message = "{negocio.descricao.notBlank}")
    @Size(min = 15, max = 512, message = "{negocio.descricao.size}")
    private String descricao;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "{negocio.usuario.notNull}")
    private Usuario usuario;
    
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