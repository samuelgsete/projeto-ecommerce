package br.com.samuel.ecommerce.models;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vendedor extends EntidadeBase {
    
    @Column
    @NotNull(message = "{vendedor.nomeFantasia.notNull}")
    @NotBlank(message = "{vendedor.nomeFantasia.notBlank}")
    @Size(min = 4, max = 60, message = "{vendedor.nome.size}")
    private String nomeFantasia;

    @Column
    @NotNull(message = "{vendedor.descricao.notNull}")
    @NotBlank(message = "{vendedor.descricao.notBlank}")
    @Size(min = 15, max = 512, message = "{vendedor.descricao.size}")
    private String descricao;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "{vendedor.usuario.notNull}")
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