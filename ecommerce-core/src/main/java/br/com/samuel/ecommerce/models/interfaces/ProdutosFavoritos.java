package br.com.samuel.ecommerce.models.interfaces;

import br.com.samuel.ecommerce.models.Produto;

public interface ProdutosFavoritos {
    Integer getQuantidade();
    Produto getProduto();
}