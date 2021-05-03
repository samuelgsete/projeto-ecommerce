package br.com.samuel.eccommerce.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.samuel.eccommerce.models.enuns.SituacaoPedido;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pedido extends EntidadeBase {

    @Column
    @NotNull(message = "{pedido.custo.notNull}")
    @DecimalMin(value = "0", message = "{pedido.preco.min}")
    private double custo;

    @NotNull(message = "pedido.feitoEm.notNull")
    @Column(name = "feito_em", columnDefinition = "DATE")
    private LocalDateTime feitoEm = LocalDateTime.now();

    @Valid
    @OneToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "{pedido.cliente.notNull}")
    private Cliente cliente;

    @Valid
    @NotNull(message = "{pedido.itens.notNull}")
    @OneToMany(mappedBy = "pedido", cascade= CascadeType.ALL)
    private Set<ItemPedido> itens = new HashSet<ItemPedido>();

    @NotNull(message = "{pedido.situacao.notNull}")
    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao;

    @Column
    private Integer negocioId;
}