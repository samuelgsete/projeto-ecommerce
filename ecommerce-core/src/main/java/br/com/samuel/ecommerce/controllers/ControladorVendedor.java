package br.com.samuel.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.ecommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.ecommerce.models.Vendedor;
import br.com.samuel.ecommerce.services.ServicoVendedor;

import javax.validation.Valid;

@RestController
@RequestMapping("/vendedor")
public class ControladorVendedor {
 
    @Autowired
    private ServicoVendedor servicoVendedor;

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarVendedorPorId(@PathVariable Integer id) {
        return servicoVendedor.buscarVendedorPorId(id);
    }

    @GetMapping
    public Page<Vendedor> buscarProdutosPaginado(@RequestParam String filtro, Pageable pageable) { 
        return servicoVendedor.listarNegociosFiltrandoPeloNomeFantasia(filtro, pageable); 
    }

    @PostMapping
    public ResponseEntity<Object> criarVendedor(@RequestBody @Valid Vendedor negocio) throws EmailCadastradoException {
        return servicoVendedor.criarVendedor(negocio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> atualizarVendedor(@PathVariable Integer id, @RequestBody @Valid Vendedor negocio) {
        return servicoVendedor.atualizarVendedor(id, negocio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerVendedor(@PathVariable Integer id) {
        return servicoVendedor.removerVendedor(id);
    }
}