package br.com.samuel.ecommerce.models.interfaces;

import java.time.LocalDateTime;

public interface HistoricoCliente {
    String getNomeCliente();
    LocalDateTime getDataCompra();
    Integer getQuantidade();
    double getPreco();
    String getUrlImagem();
    String getNomeProduto();
}