package br.com.samuel.eccommerce.models.enuns.interfaces;

import br.com.samuel.eccommerce.models.Produto;

public interface ProdutosFavoritos {
    Integer getQuantidade();
    Produto getProduto();
}