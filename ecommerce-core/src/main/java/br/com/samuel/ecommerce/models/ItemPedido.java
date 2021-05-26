package br.com.samuel.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemPedido extends EntidadeBase {

    @Valid
    @OneToOne
    @JoinColumn(name = "produto_id")
    @NotNull(message = "{itemPedido.produto.notnull}")
    private Produto produto;

    @Column
    @DecimalMin(value = "0", message = "{itemPedido.quantidade.min}")
    @NotNull(message = "{itemPedido.quantidade.notNull}")
    private Integer quantidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pedido_id", referencedColumnName="id")
    private Pedido pedido;
}