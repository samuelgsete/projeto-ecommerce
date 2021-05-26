package br.com.samuel.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.samuel.ecommerce.models.Produto;
import br.com.samuel.ecommerce.repository.RepositorioProduto;

import java.net.URI;

@Service
public class ServicoProduto {
    
    @Autowired
    private RepositorioProduto repositorioProduto;

    public ResponseEntity<Produto> buscarProdutoPorId(Integer id) {
        return repositorioProduto
                    .findById(id)
                    .map(produto -> ResponseEntity.ok().body(produto))
                    .orElse(ResponseEntity.notFound().build());
    }

    public Page<Produto> listarProdutosPorIdNegocio(Integer negocioId, String filtro, Pageable pageable) {
        if(filtro.equals("populares")) {
            return repositorioProduto.listarProdutosPopulares(negocioId, pageable);
        }
        else if(filtro.equals("menor_preco")) {
            return repositorioProduto.listarProdutosMenorPreco(negocioId, pageable);
        }
        else if(filtro.equals("maior_preco")) {
            return repositorioProduto.listarProdutosMaiorPreco(negocioId, pageable);
        }
        return repositorioProduto.listarProdutosPorIdNegocio(negocioId, pageable);
    }

    public ResponseEntity<Object> criarProduto(Produto produto) {
        var produtoCriado = repositorioProduto.save(produto);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(produtoCriado.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Produto> atualizarProduto(Integer id, Produto produto) {
        return repositorioProduto
                .findById(id)
                .map(produtoDesatualizado -> {
                    produtoDesatualizado.setNome(produto.getNome());
                    produtoDesatualizado.setPreco(produto.getPreco());
                    produtoDesatualizado.setDetalhes(produto.getDetalhes());
                    produtoDesatualizado.setUnidadesVendidas(produto.getUnidadesVendidas());
                    produtoDesatualizado.setEstoque(produto.getEstoque());
                    produtoDesatualizado.setUrlImagem(produto.getUrlImagem());
                    produtoDesatualizado.setVendedorId(produto.getVendedorId());
                    var produtoAtualizado = repositorioProduto.save(produtoDesatualizado);
                    return ResponseEntity.ok().body(produtoAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Produto> atualizarEstoque(Integer id, Integer estoque) {
        return repositorioProduto
            .findById(id)
            .map(produtoDesatualizado -> {
                produtoDesatualizado.setEstoque(estoque);
                var produtoAtualizado = repositorioProduto.save(produtoDesatualizado);
                return ResponseEntity.ok().body(produtoAtualizado);
            }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> removerProduto(Integer id) {
        return repositorioProduto
                .findById(id)
                .map(produtoRemovido -> {
                    repositorioProduto.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}