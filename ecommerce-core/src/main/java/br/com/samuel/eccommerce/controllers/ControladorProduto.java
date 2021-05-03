package br.com.samuel.eccommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import br.com.samuel.eccommerce.models.Produto;
import br.com.samuel.eccommerce.services.ServicoProduto;

@RestController
@RequestMapping("/produtos")
public class ControladorProduto {

    @Autowired
    private ServicoProduto servicoProduto;
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return servicoProduto.buscarProdutoPorId(id);
    }

    @GetMapping
    public Page<Produto> listarProdutosPorIdNegocio(@RequestParam Integer negocioId, @RequestParam String filtro, Pageable pageable) { 
        return servicoProduto.listarProdutosPorIdNegocio(negocioId, filtro, pageable); 
    }

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody @Valid Produto produto) {
        return servicoProduto.criarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        return servicoProduto.atualizarProduto(id, produto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizarEstoque(@PathVariable Integer id, @RequestBody Integer estoque) {
        return servicoProduto.atualizarEstoque(id, estoque);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable Integer id) {
        return servicoProduto.removerProduto(id);
    }
}