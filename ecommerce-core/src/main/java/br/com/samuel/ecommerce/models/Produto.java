package br.com.samuel.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
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
public class Produto extends EntidadeBase {

    @Column
    @NotBlank(message = "{produto.nome.notBlank}")
    @NotNull(message = "{produto.nome.notNull}")
    @Size(min = 4, max= 255, message = "{produto.nome.size}")
    private String nome;

    @Column
    @NotNull(message = "{produto.preco.notNull}")
    @DecimalMin(value = "0", message = "{produto.preco.min}")
    private double preco;

    @Column
    @DecimalMin(value = "0", message = "{produto.quantidadeVendas.min}")
    private Integer unidadesVendidas;

    @Column
    @NotNull(message = "{produto.estoque.notNull}")
    @DecimalMin(value = "0", message = "{produto.estoque.min}")
    private Integer estoque;

    @Column(nullable = true)
    @Size(min = 4, max = 512, message = "{produto.detalhes.size}")
    private String detalhes;

    @Column
    private String urlImagem;

    @Column
    private Integer vendedorId;
}