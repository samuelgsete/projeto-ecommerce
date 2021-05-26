package br.com.samuel.ecommerce.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "{cliente.usuario.notNull}")
    private Usuario usuario;

    @Column
    private Integer totalPedidos;

    @Column
    private Integer totalConsumido;

    @Column 
    private double totalGasto;

    @Column
    private Integer vendedorId;
}